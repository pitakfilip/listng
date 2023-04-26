package sk.fmfi.listng.rest.security.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import sk.fmfi.listng.rest.security.user.role.AppCourseRole;
import sk.fmfi.listng.rest.security.user.role.AppUserRole;
import sk.fmfi.listng.rest.security.user.role.UserRole;
import sk.fmfi.listng.user.dto.UserAuthDto;

import java.util.*;

public class AppUser implements UserDetails {

    private Long id;

    private String username; // email

    private String fullname;
    
    @JsonIgnore
    private String password;

    private Collection<AppAuthority> authorities = Collections.emptyList();

    public AppUser() {
    }

    public AppUser(Long id, String username, String fullname, String password, Collection<AppAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.fullname = fullname;
        this.password = password;
        this.authorities = Objects.requireNonNull(authorities);
    }
    
    public static AppUser build(UserAuthDto dto) {
        if (dto == null) {
            return null;
        }
        
        List<AppAuthority> authorities = new ArrayList<>(); 
        dto.permissions.forEach(permission -> {
            UserRole role = new AppCourseRole(permission.courseId, permission.role);
            authorities.add(new AppAuthority(role));
        });
        
        UserRole systemRole = AppUserRole.nameOf(dto.role);
        authorities.add(new AppAuthority(systemRole));
        
        UserRole systemRole1 = AppUserRole.nameOf(dto.role);
        return new AppUser(dto.id, dto.email, dto.name, dto.password, authorities);
    }    

    public static void setUserToSecurityContext(AppUser user) {
        if (user == null) {
            SecurityContextHolder.clearContext();
            return;
        }

        SecurityContextHolder.getContext().setAuthentication(
                new PreAuthenticatedAuthenticationToken(user, null, user.getAuthorities()));
    }

    public static Optional<AppUser> currentAppUserOptional() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return Optional.empty();
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof AppUser) {
            return Optional.of((AppUser) principal);
        } else {
            return Optional.empty();
        }
    }

    public Long getId() {
        return id;
    }

    public String getFullname() {
        return fullname;
    }

    @Override
    public Collection<AppAuthority> getAuthorities() {
        return authorities;
    }

    public String[] getRoleNames() {
        if (authorities == null) {
            return new String[]{};
        }

        return authorities.stream()
                .map(auth -> auth.getRole().getName())
                .toArray(String[]::new);
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean hasRole(UserRole role) {
        return authorities.stream()
                .anyMatch(a -> a.getRole().equals(role));
    }

    /**
     * Return true, if user has at least one of given roles.
     */
    public boolean hasAnyRole(UserRole... roles) {
        return Arrays.stream(roles)
                .anyMatch(this::hasRole);
    }

    @Override
    public String toString() {
        return "User '" + username + "' with roles " + authorities;
    }
}
