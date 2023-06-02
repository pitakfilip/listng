package sk.fmfi.listng.user.dto;

import sk.fmfi.listng.infrastructure.common.dto.BaseDto;
import sk.fmfi.listng.user.enums.SystemRole;

import java.util.List;

public class UserAuthDto implements BaseDto {

    public long id;

    public String name;

    public String email;

    public String password;

    public SystemRole role;

    public List<PermissionDto> permissions;
}
