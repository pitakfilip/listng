package sk.fmfi.listng.rest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import jakarta.validation.Valid;

import sk.fmfi.listng.rest.common.BaseController;
import sk.fmfi.listng.rest.controller.payload.request.LoginRequest;
import sk.fmfi.listng.rest.security.auth.JwtUtils;
import sk.fmfi.listng.rest.security.user.AppUser;

@RestController
@RequestMapping("/auth")
public class AuthController extends BaseController {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private JwtUtils jwtUtils;    

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            AppUser userDetails = (AppUser) authentication.getPrincipal();

            ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
            
            return ResponseEntity.ok()
                    .header("X_AUTH_TOKEN", jwtCookie.toString())
                    .body(success());
            
        } catch (Exception e) {
            return ResponseEntity.ok(error());
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(success("User has been signed out!"));
    }
    
    @PostMapping("/reset/request")
    public void reset(){
        
    }
}
