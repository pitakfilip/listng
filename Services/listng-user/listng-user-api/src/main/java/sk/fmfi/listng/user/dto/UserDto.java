package sk.fmfi.listng.user.dto;

import sk.fmfi.listng.infrastructure.common.dto.BaseDto;
import sk.fmfi.listng.user.enums.SystemRole;

import java.util.List;

public class UserDto implements BaseDto {
    
    public Long id;

    public String name;

    public String email;

    public String role;

    public List<PermissionDto> permissions;
}
