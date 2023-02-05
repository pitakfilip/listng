package sk.fmfi.listng.domain.user;

/**
 * Enum Role určuje užívateľovi pristupové práva do systému.
 */

public enum Role {
    ROOT,
    TEACHER,
    STUDENT;

    public static Role of (int ord) {
        switch (ord){
            case 0:
                return Role.ROOT;
            case 1:
                return Role.TEACHER;
            default:
                return Role.STUDENT;
        }
    }
}
