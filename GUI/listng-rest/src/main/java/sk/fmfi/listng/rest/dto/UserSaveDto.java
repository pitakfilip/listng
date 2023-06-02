package sk.fmfi.listng.rest.dto;

import sk.fmfi.listng.user.dto.PermissionDto;
import sk.fmfi.listng.user.enums.SystemRole;
import sk.fmfi.listng.user.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

public class UserSaveDto {
    
    public UserBaseDto user;
    public List<PermissionDto> permissions;

    public UserSaveDto() {
        this.permissions = new ArrayList<>();
    }

    public void setUser(UserBaseDto user) {
        this.user = user;
    }

    public void setPermissions(List<PermissionDto> permissions) {
        this.permissions = permissions;
    }
    
    public UserDto toUserDto() {
        UserDto dto = new UserDto();
        dto.id = this.user.id;
        dto.name = this.user.name;
        dto.email = this.user.email;
        dto.role = SystemRole.valueOf(this.user.role);
        dto.permissions = this.permissions;
        return dto;
    }
}
