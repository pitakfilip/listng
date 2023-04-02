package sk.fmfi.listng.rest.security.auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sk.fmfi.listng.rest.api.user.UserAuthService;
import sk.fmfi.listng.rest.security.user.AppUser;
import sk.fmfi.listng.user.api.dto.UserAuthDto;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private UserAuthService userAuthService;

    private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();

    @Override
    public AppUser loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAuthDto userAuthDto =  userAuthService.login(username);
        final AppUser user;
        user = AppUser.build(userAuthDto);
        
        if (user == null) {
            throw new UsernameNotFoundException("User with login email '" + username + "' not found.");
        }
        detailsChecker.check(user);
        
        return user;
    }
}
