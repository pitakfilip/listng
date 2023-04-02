package sk.fmfi.listng.rest.security.auth;

import java.security.*;
import java.util.Date;

import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;
import sk.fmfi.listng.rest.security.user.AppUser;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import io.jsonwebtoken.*;

@Component
public class JwtUtils {

    @Value("${app.jwt.secret}")
    private String jwtSecret;
    
    private static final long ONE_HOUR = 1000L * 60 * 60;

    @Value("${app.jwt.cookieName}")
    private String jwtCookie;
    
    private final String keyAlgorithm = "RSA";
    
    private PrivateKey privateKey;
    
    private PublicKey publicKey;

    public JwtUtils() {
        try {
            KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance(keyAlgorithm);
            keyGenerator.initialize(2048);

            KeyPair kp = keyGenerator.generateKeyPair();
            privateKey = kp.getPrivate();
            publicKey = kp.getPublic();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public String getJwtFromCookies(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, jwtCookie);
        if (cookie != null) {
            return cookie.getValue();
        } else {
            return null;
        }
    }

    public ResponseCookie generateJwtCookie(AppUser userPrincipal) {
        String jwt = generateTokenFromUsername(userPrincipal.getUsername());
        ResponseCookie cookie = ResponseCookie.from(jwtCookie, jwt).path("/api").maxAge(24 * 60 * 60).httpOnly(true).build();
        return cookie;
    }

    public ResponseCookie getCleanJwtCookie() {
        ResponseCookie cookie = ResponseCookie.from(jwtCookie, null).path("/api").build();
        return cookie;
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            System.err.println("Invalid JWT signature: " + e.getMessage());
        } catch (MalformedJwtException e) {
            System.err.println("Invalid JWT token: " + e.getMessage());
        } catch (ExpiredJwtException e) {
            System.err.println("JWT token is expired: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.err.println("JWT token is unsupported: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("JWT claims string is empty: " + e.getMessage());
        }

        return false;
    }

    public String generateTokenFromUsername(String username) {
        Date now = new Date();
        Date hourLater = new Date(now.getTime() + ONE_HOUR);
        String token =  Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(hourLater)
                .signWith(privateKey, SignatureAlgorithm.RS512)
                .compact();
        
        return token;
    }
}
