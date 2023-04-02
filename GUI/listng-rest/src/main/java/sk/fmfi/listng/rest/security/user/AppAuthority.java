package sk.fmfi.listng.rest.security.user;

import org.springframework.security.core.GrantedAuthority;
import sk.fmfi.listng.rest.security.user.role.AppUserRole;
import sk.fmfi.listng.rest.security.user.role.UserRole;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class AppAuthority implements GrantedAuthority {

    private final transient UserRole authority;

    AppAuthority(UserRole authority) {
        this.authority = Objects.requireNonNull(authority);
    }
    
    private static AppAuthority forName(String roleName) {
        UserRole userRole = AppUserRole.nameOf(roleName);
        return new AppAuthority(userRole);
    }

    public static List<AppAuthority> fromRoleNames(Collection<String> roleNames) {
        return roleNames == null ? List.of() : roleNames.stream()
                .map(AppAuthority::forName)
                .sorted(Comparator.comparing(AppAuthority::getAuthority))
                .toList();
    }

    @Override
    public String getAuthority() {
        return authority.getName();
    }

    public UserRole getRole() {
        return authority;
    }

    @Override
    public String toString() {
        return authority.getName();
    }
}
