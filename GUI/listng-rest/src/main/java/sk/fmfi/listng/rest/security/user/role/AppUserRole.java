package sk.fmfi.listng.rest.security.user.role;

import sk.fmfi.listng.domain.enums.SystemRole;

public class AppUserRole implements UserRole {
    
    private final SystemRole role;

    AppUserRole(SystemRole role) {
        this.role = role;
    }

    @Override
    public String getName() {
        return role.getName();
    }

    public SystemRole getRole() {
        return role;
    }
    
    @Override
    public Long getIdentifier(){
        throw new UnsupportedOperationException("Not able to provide identifier for given UserRole type");
    }

    public static UserRole nameOf(String name) {
        for (SystemRole role : SystemRole.values()) {
            if (role.getName().equals(name)) {
                return new AppUserRole(role);
            }
        }

        return null;
    }
}
