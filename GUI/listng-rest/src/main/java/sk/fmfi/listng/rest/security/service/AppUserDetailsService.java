package sk.fmfi.listng.rest.security.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import sk.fmfi.listng.rest.proxy.user.UserAuthApiProxy;
import sk.fmfi.listng.rest.security.cache.UserCacheService;
import sk.fmfi.listng.rest.security.user.AppUser;
import sk.fmfi.listng.user.dto.UserAuthDto;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private UserAuthApiProxy userAuthApiProxy;
    
    @Autowired
    private UserCacheService userCacheService;

    private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();
    
    @Override
    public AppUser loadUserByUsername(String username) throws UsernameNotFoundException {
        if (userCacheService.haveValidInCache(username)) {
            return userCacheService.getFromCache(username);
        }

        UserAuthDto userAuthDto = userAuthApiProxy.getAuthUserByEmail(username);
        if (userAuthDto == null) {
            throw new UsernameNotFoundException("User with login email '" + username + "' not found.");
        }
        
        final AppUser user;
        user = AppUser.build(userAuthDto);
        
        detailsChecker.check(user);
        userCacheService.storeUser(user);
        
        return user;
    }
}
