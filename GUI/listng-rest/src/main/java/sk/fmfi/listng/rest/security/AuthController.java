package sk.fmfi.listng.rest.security;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import jakarta.validation.Valid;

import sk.fmfi.listng.rest.common.ListController;
import sk.fmfi.listng.rest.security.payload.LoginRequest;
import sk.fmfi.listng.rest.security.auth.JwtUtils;
import sk.fmfi.listng.rest.security.user.AppUser;

@RestController
@RequestMapping("/auth")
public class AuthController extends ListController {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private JwtUtils jwtUtils;

    
    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@Valid @RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            AppUser userDetails = (AppUser) authentication.getPrincipal();

            Cookie cookie = jwtUtils.generateJwtCookie(userDetails);            
            response.addCookie(cookie);
            
            return ResponseEntity.ok(success(cookie.getValue()));
        } catch (Exception e) {
            return ResponseEntity.ok(error());
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        Cookie cookie = jwtUtils.getCleanJwtCookie();
        response.addCookie(cookie);
        
        return ResponseEntity.ok().body(success("User has been signed out!"));
    }
    
    @PostMapping("/reset/request")
    public void reset(){
        // TODO poziadat o reset hesla (email)
    }
}
