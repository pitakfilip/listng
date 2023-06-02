package sk.fmfi.listng.user.dto;

import sk.fmfi.listng.infrastructure.common.dto.BaseDto;
import sk.fmfi.listng.user.enums.CourseRole;
import sk.fmfi.listng.user.enums.CourseStatus;

public class PermissionDto implements BaseDto {
    
    public Long id;
    
    public long userId;
    
    public long courseId;
    
    public CourseRole role;
    
    public CourseStatus status;
    
    public Long groupId;
}
