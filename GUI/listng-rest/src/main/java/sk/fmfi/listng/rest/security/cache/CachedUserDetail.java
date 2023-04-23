package sk.fmfi.listng.rest.security.cache;

import sk.fmfi.listng.rest.security.user.AppUser;

import java.util.Date;

public record CachedUserDetail(AppUser user, Date cached) {
    
    public static CachedUserDetail create(AppUser user){
        return new CachedUserDetail(user, new Date());
    }
    
    private static final long TEN_MINUTES = 1000L * 60 * 10;
    
    // V cache chceme mat ulozene objekty UserDetails na 10min.
    public boolean isValid() {
        return new Date().before(new Date(cached.getTime() + TEN_MINUTES));
    }
}
