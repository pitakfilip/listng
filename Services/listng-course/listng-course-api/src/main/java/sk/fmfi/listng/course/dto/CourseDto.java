package sk.fmfi.listng.course.dto;

import sk.fmfi.listng.infrastructure.common.dto.BaseDto;
import sk.fmfi.listng.infrastructure.common.dto.MultiLangTextDto;

import java.util.List;

public class CourseDto implements BaseDto {
    public long id;
    public MultiLangTextDto name;
    public MultiLangTextDto abbreviation;
    public Long periodId;
    public List<GroupDto> groups;
}
