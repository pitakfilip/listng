package sk.fmfi.listng.user.application.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.fmfi.listng.domain.user.User;
import sk.fmfi.listng.user.api.UserAuthApi;
import sk.fmfi.listng.user.application.assembler.UserAssembler;
import sk.fmfi.listng.user.application.service.UserAdminService;
import sk.fmfi.listng.user.application.service.UserService;
import sk.fmfi.listng.user.application.util.PasswordGenerator;
import sk.fmfi.listng.user.dto.UserAuthDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController("/auth")
@RequestMapping("/auth")
public class UserAuthController implements UserAuthApi {

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

    @Override
    public void newUser(UserAuthDto dto) {
        User user = UserAssembler.fromAuthDto(dto);
        String generatedPassword = PasswordGenerator.create();
        user.setPassword(passwordEncoder.encode(generatedPassword));
        
        userService.save(user);
        
        userAdminService.sendNewAccountNotification(user.getEmail(), generatedPassword);
    }

    @Override
    public void newUsers(List<UserAuthDto> dtos) {
        Map<String, String> generatedPasswords = new HashMap<>();
        List<User> users = dtos.stream()
                .map(user -> {
                    user.password = PasswordGenerator.create();
                    generatedPasswords.put(user.email, user.password);
                    user.password = passwordEncoder.encode(user.password);
                    return UserAssembler.fromAuthDto(user);
                })
                .collect(Collectors.toList());
        
        for (User saved : userService.save(users)) {
            userAdminService.sendNewAccountNotification(saved.getEmail(),
                    generatedPasswords.get(saved.getEmail()));
        }
    }
}
