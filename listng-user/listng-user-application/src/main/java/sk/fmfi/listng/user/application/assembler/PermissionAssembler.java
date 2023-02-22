package sk.fmfi.listng.user.application.assembler;

import sk.fmfi.listng.domain.user.Permission;
import sk.fmfi.listng.domain.user.User;

import java.util.List;

public class PermissionAssembler {

    private PermissionAssembler() {
        throw new IllegalStateException("Utility class");
    }

//    public static Permission fromEntity(DtoPermission entity) {
//        return null;
//    }
//
//    public static List<Permission> fromEntities(List<DtoPermission> entities) {
//        return entities.stream()
//                .map(PermissionAssembler::fromEntity)
//                .toList();
//    }
//
//    public static DtoPermission toEntity(User user, Permission permission) {
//        DtoPermission entity = new DtoPermission();
//        entity.setUserId(user.getId());
//        entity.setCourseId(permission.getCourse().getId());
//
//        return entity;
//    }
}
