package sk.fmfi.listng.course.application.assembler;

import sk.fmfi.listng.course.dto.CourseDto;
import sk.fmfi.listng.domain.course.Course;
import sk.fmfi.listng.infrastructure.common.dto.MultiLangTextDto;

import java.util.List;

public class CourseAssembler {

    public static CourseDto toDto(Course course) {
        CourseDto dto = new CourseDto();
        dto.id = course.getId();
        dto.name = MultiLangTextDto.toDto(course.getName());
        dto.abbreviation = MultiLangTextDto.toDto(course.getAbbreviation());
        dto.periodId = course.getPeriod();
        dto.groups = GroupAssembler.toDto(course.getGroups().stream().toList());
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
        return course;
    }
}
