package sk.fmfi.listng.user.dto;

import sk.fmfi.listng.infrastructure.common.dto.BaseDto;

import java.util.List;

public class UsersOperationDto implements BaseDto {
    
    public String role;

    public List<UserDto> users; // name and email only!

    public List<PermissionDto> permissions;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<UserDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserDto> users) {
        this.users = users;
    }

    public List<PermissionDto> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionDto> permissions) {
        this.permissions = permissions;
    }
}
