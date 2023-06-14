package sk.fmfi.listng.course.dto;

import sk.fmfi.listng.infrastructure.common.dto.BaseDto;
import sk.fmfi.listng.infrastructure.common.dto.MultiLangTextDto;

import java.util.List;

public class GroupDto implements BaseDto {
    public long id;
    public boolean isDefault;
    public int capacity;
    public long courseId;
    public MultiLangTextDto name;
    public List<ClassDto> classes;
}
