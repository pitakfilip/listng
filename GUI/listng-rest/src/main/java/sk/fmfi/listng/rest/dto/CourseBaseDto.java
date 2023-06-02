package sk.fmfi.listng.rest.dto;

import sk.fmfi.listng.course.dto.CourseDto;
import sk.fmfi.listng.infrastructure.common.dto.BaseDto;
import sk.fmfi.listng.infrastructure.common.dto.MultiLangTextDto;

import java.util.List;
import java.util.stream.Collectors;

public class CourseBaseDto implements BaseDto {
    public long id;
    public long periodId;
    public MultiLangTextDto name;
    public MultiLangTextDto abbreviation;
    
    public static CourseBaseDto of(CourseDto apiDto) {
        CourseBaseDto dto = new CourseBaseDto();
        dto.id = apiDto.id;
        dto.periodId = apiDto.periodId;
        dto.name = apiDto.name;
        dto.abbreviation = apiDto.abbreviation;
        
        return dto;
    }
    
    public static List<CourseBaseDto> of(List<CourseDto> dtos) {
        return dtos.stream()
                .map(CourseBaseDto::of)
                .collect(Collectors.toList());
    }
}
