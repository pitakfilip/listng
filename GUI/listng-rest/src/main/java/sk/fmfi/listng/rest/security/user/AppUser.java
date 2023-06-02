package sk.fmfi.listng.rest.security.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import sk.fmfi.listng.rest.security.user.role.AppUserRole;
import sk.fmfi.listng.rest.security.user.role.UserRole;
import sk.fmfi.listng.user.dto.UserAuthDto;

import java.util.*;

public class AppUser implements UserDetails {

    private Long id;

    private String username; // email

    private String fullname;
    
    private String role;
    
    @JsonIgnore
    private String password;

    @JsonIgnore
    private Collection<AppAuthority> authorities = Collections.emptyList();

    public AppUser() {
    }

    public AppUser(Long id, String username, String fullname, String password, Collection<AppAuthority> authorities, String role) {
        this.id = id;
        this.username = username;
        this.fullname = fullname;
        this.password = password;
        this.authorities = Objects.requireNonNull(authorities);
        this.role = role;
    }
    
    public static AppUser build(UserAuthDto dto) {
        if (dto == null) {
            return null;
        }
        
        UserRole systemRole = AppUserRole.of(dto.role.name());
        return new AppUser(dto.id, dto.email, dto.name, dto.password, List.of(new AppAuthority(systemRole)), dto.role.name());
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

    public String getSysRole() {
        return this.role;
    }

    @Override
    public String toString() {
        return "User '" + username + "' with roles " + authorities;
    }
}
