package sk.fmfi.listng.course.application.assembler;

import sk.fmfi.listng.course.domain.Course;
import sk.fmfi.listng.course.dto.CourseDto;
import sk.fmfi.listng.infrastructure.common.dto.MultiLangTextDto;

import java.util.HashSet;
import java.util.List;

public class CourseAssembler {

    public static CourseDto toDto(Course course) {
        CourseDto dto = new CourseDto();
        dto.id = course.getId();
        dto.name = new MultiLangTextDto(course.getName());
        dto.abbreviation = new MultiLangTextDto(course.getAbbreviation());
        dto.periodId = course.getPeriod();
        if (course.getGroups() != null) {
            dto.groups = GroupAssembler.toDto(course.getGroups());    
        }
        return dto;
    }

    public static List<CourseDto> toDto(List<Course> courses) {
        return courses.stream()
                .map(CourseAssembler::toDto)
                .toList();
    }

    public static Course fromDto(CourseDto dto) {
        Course course = new Course();
        course.setId(dto.id);
        course.setName(MultiLangTextDto.fromDto(dto.name));
        course.setAbbreviation(MultiLangTextDto.fromDto(dto.abbreviation));
        course.setPeriod(dto.periodId);
        if (dto.groups != null) {
            course.setGroups(new HashSet<>(GroupAssembler.fromDto(dto.groups)));
        }
        return course;
    }
}
