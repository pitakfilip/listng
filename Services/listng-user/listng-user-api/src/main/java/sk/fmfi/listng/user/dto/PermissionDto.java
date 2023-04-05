package sk.fmfi.listng.user.dto;

import sk.fmfi.listng.infrastructure.common.BaseDto;

public class PermissionDto implements BaseDto {
    
    public long id;
    
    public long courseId;
    
    public String role;
    
    public String status;
    
    public long groupId;
}
