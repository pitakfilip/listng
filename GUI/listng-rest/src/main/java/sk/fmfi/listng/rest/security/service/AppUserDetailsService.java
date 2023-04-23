package sk.fmfi.listng.rest.security.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sk.fmfi.listng.infrastructure.common.Response;
import sk.fmfi.listng.rest.proxy.UserApiProxy;
import sk.fmfi.listng.rest.security.cache.UserCacheService;
import sk.fmfi.listng.rest.security.user.AppUser;
import sk.fmfi.listng.user.dto.UserAuthDto;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private UserApiProxy userApiProxy;
    
    @Autowired
    private UserCacheService userCacheService;

    private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();
    
    @Override
    public AppUser loadUserByUsername(String username) throws UsernameNotFoundException {
        if (userCacheService.haveValidInCache(username)) {
            return userCacheService.getFromCache(username);
        }

        Response<UserAuthDto> response = userApiProxy.getAuthUserByEmail(username);
        if (!response.isSuccess()) {
            throw new UsernameNotFoundException("User with login email '" + username + "' not found.");
        }
        
        UserAuthDto userAuthDto = response.getPayload();
        final AppUser user;
        user = AppUser.build(userAuthDto);
        
        detailsChecker.check(user);
        userCacheService.storeUser(user);
        
        return user;
    }
}
