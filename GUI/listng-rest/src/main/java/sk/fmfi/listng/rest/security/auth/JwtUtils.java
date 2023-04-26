package sk.fmfi.listng.rest.security.auth;

import java.security.*;
import java.util.*;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.impl.DefaultClaims;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;
import javax.crypto.spec.SecretKeySpec;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import io.jsonwebtoken.*;

import sk.fmfi.listng.rest.controller.payload.response.CoursePermission;
import sk.fmfi.listng.rest.security.user.AppUser;
import sk.fmfi.listng.rest.security.user.role.AppCourseRole;
import sk.fmfi.listng.rest.security.user.role.AppUserRole;
import sk.fmfi.listng.rest.security.user.role.UserRole;

@Component
public class JwtUtils {

    @Value("${app.jwt.secret}")
    private String jwtSecret;

    @Value("${app.jwt.cookieName}")
    private String jwtCookieName;

    // PRODUCTION
    private static final long ONE_HOUR_SECONDS = 60 * 60;
    private static final long ONE_HOUR_MILLI = 1000L * ONE_HOUR_SECONDS;

    // FOR DEVELOPMENT
    private static final long ONE_DAY_SECONDS = 60 * 60 * 24;
    private static final long ONE_DAY_MILLI = 1000L * ONE_HOUR_SECONDS;
    
    private static final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    private Key signingKey;
    
    @PostConstruct
    public void postConstruct(){
        byte[] apiSecretBytes = DatatypeConverter.parseBase64Binary(jwtSecret);
        signingKey = new SecretKeySpec(apiSecretBytes, signatureAlgorithm.getJcaName());
    }

    public String getJwtFromCookies(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, jwtCookieName);
        if (cookie != null) {
            return cookie.getValue();
        } else {
            return null;
        }
    }

    public ResponseCookie generateJwtCookie(AppUser userPrincipal) {
        String jwt = generateTokenFromUser(userPrincipal);
        return ResponseCookie.from(jwtCookieName, jwt)
                .path("/api")
                .maxAge(ONE_DAY_SECONDS)
                .httpOnly(false)
                .build();
    }

    public ResponseCookie getCleanJwtCookie() {
        return ResponseCookie.from(jwtCookieName, null)
                .path("/api")
                .build();
    }

    public String getUserNameFromJwtToken(String jwt) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(jwt)
                .getBody();
        
        return claims.get("USER_MAIL", String.class);
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(signingKey)
                    .build()
                    .parseClaimsJws(authToken);
            
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

    public String generateTokenFromUser(AppUser user) {   
        return Jwts.builder()
                .setClaims(makeClaimsForUser(user))
                .signWith(signingKey, signatureAlgorithm)
                .compact();
    }
    
    private Claims makeClaimsForUser(AppUser user) {
        Claims claims = new DefaultClaims();
        claims.put("id", user.getId());
        claims.put("name", user.getFullname());
        claims.put("mail", user.getUsername());
        
        List<CoursePermission> courseRoles = new ArrayList<>();

        user.getAuthorities()
                .forEach(authority -> {
            UserRole role = authority.getRole();
            
            if (role instanceof AppUserRole) {
                claims.put("role", ((AppUserRole) role).getRole().getName());
            } else if (role instanceof AppCourseRole) {
                CoursePermission permission = new CoursePermission(role.getIdentifier(), ((AppCourseRole) role).getRole());
                courseRoles.add(permission);
            }
        });
        
        if (!courseRoles.isEmpty()) {
            claims.put("permissions", courseRoles);
        }
        
        return claims;
    }
}
