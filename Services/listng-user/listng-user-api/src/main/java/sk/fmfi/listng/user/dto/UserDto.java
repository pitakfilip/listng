package sk.fmfi.listng.user.dto;

import sk.fmfi.listng.infrastructure.common.BaseDto;

import java.util.List;

public class UserDto implements BaseDto {
    
    public long id;

    public String name;

    public String email;

    public String role;

    public List<PermissionDto> permissions;
}
