package sk.fmfi.listng.user.domain;

/**
 * Enum Role určuje užívateľovi pristupové práva do systému.
 */

public enum SystemRole {

    ROOT("ROOT"),
    TEACHER("TEACHER"),
    STUDENT("STUDENT");

    private final String name;

    SystemRole(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public static SystemRole nameOf(String name) {
        for (SystemRole role : SystemRole.values()) {
            if (role.getName().equals(name)) {
                return role;
            }
        }

        return null;
    }
}
