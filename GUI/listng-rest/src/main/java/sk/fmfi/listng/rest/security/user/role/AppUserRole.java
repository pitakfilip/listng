package sk.fmfi.listng.rest.security.user.role;

public enum AppUserRole implements UserRole {
    ROOT("ROOT"),
    TEACHER("TEACHER"),
    STUDENT("STUDENT");

    private final String name;

    AppUserRole(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public static UserRole nameOf(String name) {
        for (AppUserRole role : AppUserRole.values()) {
            if (role.getName().equals(name)) {
                return role;
            }
        }

        return null;
    }
}
