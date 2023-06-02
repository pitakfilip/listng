package sk.fmfi.listng.rest.security.user;

import org.springframework.security.core.GrantedAuthority;
import sk.fmfi.listng.rest.security.user.role.UserRole;

import java.util.Objects;

public class AppAuthority implements GrantedAuthority {

    private final transient UserRole authority;

    AppAuthority(UserRole authority) {
        this.authority = Objects.requireNonNull(authority);
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
