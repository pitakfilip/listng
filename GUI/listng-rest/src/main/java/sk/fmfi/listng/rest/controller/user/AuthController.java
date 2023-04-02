package sk.fmfi.listng.rest.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import sk.fmfi.listng.rest.api.user.UserAuthService;
import sk.fmfi.listng.rest.controller.BaseController;
import sk.fmfi.listng.rest.controller.payload.request.LoginRequest;
import sk.fmfi.listng.rest.controller.payload.response.Response;
import sk.fmfi.listng.rest.controller.payload.response.UserInfo;
import sk.fmfi.listng.rest.proxy.UserAuthProxy;
import sk.fmfi.listng.rest.security.auth.JwtUtils;
import sk.fmfi.listng.rest.security.user.AppAuthority;
import sk.fmfi.listng.rest.security.user.AppUser;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController extends BaseController {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UserAuthService userAuthService;
    
    @Autowired
    private UserAuthProxy userAuthProxy;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;


    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        AppUser userDetails = (AppUser) authentication.getPrincipal();

        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(AppAuthority::getAuthority)
                .toList();

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(success(new UserInfo(
                        userDetails.getId(),
                        userDetails.getFullname(), 
                        userDetails.getUsername(),
                        roles
                )));
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logout() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(success("User has been signed out!"));
    }
    
    @GetMapping("dummy")
    public Response<?> dummy() {
        return success(userAuthService.login("jozef@ferko.jozo"));
    }
    
    @GetMapping("testing")
    public Response<?> test() {
        return success(userAuthProxy.getAuthUserByEmail("emaail"));
    }
}
