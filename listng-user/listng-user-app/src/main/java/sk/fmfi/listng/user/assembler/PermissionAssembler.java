package sk.fmfi.listng.user.assembler;

import sk.fmfi.listng.domain.user.Permission;
import sk.fmfi.listng.domain.user.User;
import sk.fmfi.listng.user.entity.PermissionEntity;

import java.util.List;
import java.util.stream.Collectors;

public class PermissionAssembler {

    private PermissionAssembler() {
        throw new IllegalStateException("Utility class");
    }

    public static Permission fromEntity(PermissionEntity entity) {
        return null;
    }

    public static List<Permission> fromEntities(List<PermissionEntity> entities) {
        return entities.stream()
                .map(PermissionAssembler::fromEntity)
                .toList();
    }

    public static PermissionEntity toEntity(User user, Permission permission) {
        PermissionEntity entity = new PermissionEntity();
        entity.setUserId(user.getId());
        entity.setCourseId(permission.getCourse().getId());

        return entity;
    }
}
