package sk.fmfi.listng.user.dto;

import sk.fmfi.listng.infrastructure.common.dto.BaseDto;

import java.util.List;

public class BulkOperationDto implements BaseDto {

    public String role;

    public List<Long> userIds;

    public List<PermissionDto> permissions;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Long> userIds) {
        this.userIds = userIds;
    }

    public List<PermissionDto> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionDto> permissions) {
        this.permissions = permissions;
    }
}
