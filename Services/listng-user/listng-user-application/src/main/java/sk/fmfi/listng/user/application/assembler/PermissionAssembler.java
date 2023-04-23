package sk.fmfi.listng.user.application.assembler;

import sk.fmfi.listng.domain.enums.CourseRole;
import sk.fmfi.listng.domain.enums.CourseStatus;
import sk.fmfi.listng.domain.user.Permission;
import sk.fmfi.listng.user.dto.PermissionDto;

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
        dto.courseId = permission.getCourseId();
        dto.role = permission.getRole().name();
        if (dto.status != null) {
            dto.status = permission.getStatus().name();
        }
        if (permission.getGroupId() != null) {
            dto.groupId = permission.getGroupId();
        }
        
        return dto;
    }
    
    public static List<PermissionDto> toDto(Set<Permission> permissions) {
        return permissions.stream()
                .map(PermissionAssembler::toDto)
                .toList();
    }
    
    public static Permission fromDto(PermissionDto dto) {
        Permission permission = new Permission();
        
        permission.setId(dto.id);
        permission.setCourseId(dto.courseId);
        permission.setRole(CourseRole.valueOf(dto.role));
        
        if (dto.status != null){
            permission.setStatus(CourseStatus.valueOf(dto.status));
        }
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
