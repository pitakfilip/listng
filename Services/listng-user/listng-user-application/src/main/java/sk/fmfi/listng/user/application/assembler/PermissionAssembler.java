package sk.fmfi.listng.user.application.assembler;

import sk.fmfi.listng.user.enums.CourseRole;
import sk.fmfi.listng.user.domain.Permission;
import sk.fmfi.listng.user.dto.PermissionDto;
import sk.fmfi.listng.user.enums.CourseStatus;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PermissionAssembler {

    private PermissionAssembler() {
        throw new IllegalStateException("Utility class");
    }

    public static PermissionDto toDto(Permission permission) {
        PermissionDto dto = new PermissionDto();
        dto.id = permission.getId();
        dto.userId = permission.getUserId();
        dto.courseId = permission.getCourseId();
        if (permission.getRole() != null)
            dto.role = CourseRole.valueOf(permission.getRole().name());
        if (permission.getStatus() != null)
            dto.status = CourseStatus.valueOf(permission.getStatus().name());
        dto.groupId = permission.getGroupId();

        return dto;
    }

    public static List<PermissionDto> toDto(Set<Permission> permissions) {
        return permissions.stream()
                .map(PermissionAssembler::toDto)
                .toList();
    }

    public static Permission fromDto(PermissionDto dto) {
        Permission permission = new Permission();

        if (dto.id != null) {
            permission.setId(dto.id);
        }
        permission.setUserId(dto.userId);
        permission.setCourseId(dto.courseId);
        permission.setRole(sk.fmfi.listng.user.domain.CourseRole.valueOf(dto.role.name()));
        permission.setStatus(sk.fmfi.listng.user.domain.CourseStatus.valueOf(dto.status.name()));

        if (dto.groupId != null) {
            permission.setGroupId(dto.groupId);
        }

        return permission;
    }

    public static Set<Permission> fromDto(List<PermissionDto> dtos) {
        return dtos.stream()
                .map(PermissionAssembler::fromDto)
                .collect(Collectors.toSet());
    }
}
