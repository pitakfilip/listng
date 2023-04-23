package sk.fmfi.listng.user.dto;

import sk.fmfi.listng.infrastructure.common.dto.BaseDto;

public class PermissionDto implements BaseDto {
    
    public long id;
    
    public long courseId;
    
    public String role;
    
    public String status;
    
    public Long groupId;
}
