package sk.fmfi.listng.rest.api.user;

import org.springframework.stereotype.Service;
import sk.fmfi.listng.user.api.dto.PermissionDto;
import sk.fmfi.listng.user.api.dto.UserAuthDto;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class UserAuthService {
    
    public UserAuthDto login(String email) {
        // TODO get user by email z module User
        UserAuthDto user = new UserAuthDto();
        user.id = 1;
        user.name = "Ferko Mrkvicka";
        user.email = email;
        user.password = "$2a$10$u8ZYId70oRistWaP24xfnu1Fe5kbQR7O1BfAa5ezW2ZFcJPMIirlq"; // a
        user.role = "ROOT";
        user.permissions = Collections.emptyList();
        
        return user;
    }
}
