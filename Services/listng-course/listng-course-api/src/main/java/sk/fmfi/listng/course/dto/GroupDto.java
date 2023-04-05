package sk.fmfi.listng.course.dto;

import sk.fmfi.listng.infrastructure.common.BaseDto;

public class GroupDto implements BaseDto {
    public long id;
    public CourseDto course;
    public String name;
}
