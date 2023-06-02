package sk.fmfi.listng.rest.security.user.role;

public class AppUserRole implements UserRole {
    
    private final String role;

    AppUserRole(String role) {
        this.role = role;
    }

    @Override
    public String getName() {
        return role;
    }

    public String getRole() {
        return role;
    }
    
    public static UserRole of(String role) {
        return new AppUserRole(role);
    }
}
