package sk.fmfi.listng.course.dto;

import sk.fmfi.listng.infrastructure.common.BaseDto;

public class CourseDto implements BaseDto {
    public long id;
    public String name;
    public PeriodDto period;
}
