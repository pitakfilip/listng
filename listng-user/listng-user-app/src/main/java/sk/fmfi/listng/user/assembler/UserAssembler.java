package sk.fmfi.listng.user.assembler;

import sk.fmfi.listng.domain.user.User;
import sk.fmfi.listng.user.entity.UserEntity;

public class UserAssembler {

    private UserAssembler() {
        throw new IllegalStateException("Utility class");
    }

    public static User fromEntity(UserEntity userEntity) {
        User user =  new User(userEntity.getName(), userEntity.getEmail(), userEntity.getPassword(), userEntity.getRole());

        // TODO spojit permissions s kurzom

        return user;
    }

    public static UserEntity toEntity(User user) {
        UserEntity entity = new UserEntity();

        entity.setPassword(user.getName());
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());
        entity.setRole(user.getRole().ordinal());

        if (user.isStudent()){
            // TODO set user permissions -> treba volat CourseAPI v PermissionAssembler

        }

        return entity;
    }
}
