package sk.fmfi.listng.user.application.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;
import sk.fmfi.listng.user.domain.User;
import sk.fmfi.listng.user.api.UserAuthApi;
import sk.fmfi.listng.user.application.assembler.UserAssembler;
import sk.fmfi.listng.user.application.service.UserAdminService;
import sk.fmfi.listng.user.application.service.UserService;
import sk.fmfi.listng.user.dto.UserAuthDto;


@RestController
public class AuthController implements UserAuthApi {

    @Autowired
    private UserService userService;
    
    @Autowired
    private UserAdminService userAdminService;
    
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    @Override
    public UserAuthDto getAuthUserByEmail(String email) {
        User user = userService.getAuthByEmail(email);

        if (user == null) {
            return null;
        }
        
        return UserAssembler.toAuthDto(user);
    }

    @Override
    public boolean requestPasswordReset(String email) {
        if (!userService.exists(email)) {
            throw new EntityNotFoundException("error.user.not.found");
        }
                
        return userAdminService.sendPasswordResetEmail(email);
    }

    @Override
    public void changePassword(String hash) throws Exception {
        userAdminService.processPasswordReset(hash);
    }
}
