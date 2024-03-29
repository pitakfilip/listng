package sk.fmfi.listng.rest.security.cache;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import sk.fmfi.listng.rest.security.user.AppUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@EnableScheduling
public class UserCacheService {
    
    private Map<String, CachedUserDetail> userCache = new HashMap<>();

    private static final long ONE_MINUTE = 1000L * 60; 
    
    /**
     * Lookup user in cache and verify its validity, where the max. store value of user object in cache is 10 minutes.
     * @param username
     * @return boolean found user in cache.
     */
    public boolean haveValidInCache(String username) {
        if (userCache.containsKey(username)) {
            return userCache.get(username).isValid();    
        }
        
        return false;
    }
    
    public void storeUser(AppUser user) {
        userCache.put(user.getUsername(), CachedUserDetail.create(user));
    }
    
    public AppUser getFromCache(String username) {
        return userCache.get(username).user();
    }
    
    @Scheduled(fixedDelay = ONE_MINUTE)
    private void scheduleRemoveInvalid() {
        if (!userCache.isEmpty()) {
            List<String> keys = new ArrayList<>();
            for (Map.Entry<String, CachedUserDetail> cached : userCache.entrySet()) {
                if (!cached.getValue().isValid()) {
                    keys.add(cached.getKey());
                }
            }

            for (String key : keys) {
                userCache.remove(key);
            }
        }
    }
}
