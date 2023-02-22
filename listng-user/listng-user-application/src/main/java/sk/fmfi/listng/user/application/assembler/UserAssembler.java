package sk.fmfi.listng.user.application.assembler;

import sk.fmfi.listng.domain.user.User;

public class UserAssembler {

    private UserAssembler() {
        throw new IllegalStateException("Utility class");
    }

//    public static User fromEntity(DtoUser dtoUser) {
//        User user =  new User(dtoUser.getName(), dtoUser.getEmail(), dtoUser.getPassword(), dtoUser.getRole());
//
//        // TODO spojit permissions s kurzom
//
//        return user;
//    }
//
//    public static DtoUser toEntity(User user) {
//        DtoUser entity = new DtoUser();
//
//        entity.setPassword(user.getName());
//        entity.setEmail(user.getEmail());
//        entity.setPassword(user.getPassword());
//        entity.setRole(user.getRole().ordinal());
//
//        if (user.isStudent()){
//            // TODO set user permissions -> treba volat CourseAPI v PermissionAssembler
//
//        }
//
//        return entity;
//    }
}
