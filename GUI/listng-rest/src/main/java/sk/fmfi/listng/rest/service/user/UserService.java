package sk.fmfi.listng.rest.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sk.fmfi.listng.domain.enums.SystemRole;
import sk.fmfi.listng.infrastructure.common.Response;
import sk.fmfi.listng.rest.controller.payload.response.CoursePermission;
import sk.fmfi.listng.rest.proxy.UserApiProxy;
import sk.fmfi.listng.rest.security.user.AppAuthority;
import sk.fmfi.listng.rest.security.user.AppUser;
import sk.fmfi.listng.rest.security.user.role.AppCourseRole;
import sk.fmfi.listng.rest.security.user.role.AppUserRole;
import sk.fmfi.listng.rest.security.user.role.UserRole;
import sk.fmfi.listng.user.dto.UserAuthDto;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
    
    @Autowired
    private UserApiProxy userApiProxy;
    
    
    public SystemRole getSystemRoleFromAppAuthorities(Collection<AppAuthority> authorities) {
        for (AppAuthority authority : authorities) {
            UserRole role = authority.getRole();
            if (role instanceof AppUserRole) {
                return ((AppUserRole) role).getRole();
            }
        }
        return SystemRole.STUDENT; // DEFAULT VALUE
    }
    
    public Set<CoursePermission> getCoursePermissionsFromAppAuthorities(Collection<AppAuthority> authorities){
        Set<CoursePermission> permissions = new HashSet<>();
        
        for (AppAuthority authority : authorities) {
            UserRole role = authority.getRole();
            
            if (role instanceof AppCourseRole) {
                permissions.add(new CoursePermission(role.getIdentifier(), ((AppCourseRole) role).getRole()));
            }
        }
        
        return permissions;
    }
    
}
