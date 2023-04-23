package sk.fmfi.listng.user.dto;

import sk.fmfi.listng.infrastructure.common.dto.BaseDto;

import java.util.List;

public class UserAuthDto implements BaseDto {

    public long id;

    public String name;

    public String email;

    public String password;

    public String role;

    public List<PermissionDto> permissions;
}
