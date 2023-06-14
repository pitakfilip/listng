package sk.fmfi.listng.course.application.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sk.fmfi.listng.course.api.CourseApi;
import sk.fmfi.listng.course.application.assembler.CourseAssembler;
import sk.fmfi.listng.course.application.service.*;
import sk.fmfi.listng.course.domain.Course;
import sk.fmfi.listng.course.domain.Period;
import sk.fmfi.listng.course.dto.CourseDto;
import sk.fmfi.listng.infrastructure.common.dto.PageResponse;
import sk.fmfi.listng.infrastructure.common.dto.PagingParams;
import sk.fmfi.listng.infrastructure.common.dto.SortParams;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class CourseController implements CourseApi {

    @Autowired
    private CourseService courseService;

    @Autowired
    private PeriodService periodService;

    
    @Override
    public void save(CourseDto courseDto) {
        courseService.saveCourse(courseDto);
    }

    @Override
    public void copyCourses(List<Long> courseIds, Long periodId) {
        courseService.copyCourses(courseIds, periodId);
    }

    @Override
    public CourseDto getCourseById(Long courseId) {
        Optional<Course> course = courseService.getById(courseId);
        if (course.isEmpty()) {
            throw new EntityNotFoundException("error.course.not.found");
        }
        return CourseAssembler.toDto(course.get());
    }
    
    @Override
    public PageResponse<CourseDto> getCoursePage(PagingParams params) {
        if (params.sort.isEmpty()) {
            params.sort.add(new SortParams("id"));
        }
        return courseService.getCoursePage(params);
    }
    
    @Override
    public List<CourseDto> getCourses() {
        return courseService.getAll().stream()
                .map(CourseAssembler::toDto)
                .toList();
    }

    @Override
    public List<CourseDto> getCoursesOfPeriod(Long periodId) {
        List<Course> courses = courseService.getAllInPeriod(periodId);

        if (courses == null || courses.isEmpty()) {
            return Collections.emptyList();
        }

        return CourseAssembler.toDto(courses);
    }

    @Override
    public List<CourseDto> getActiveCourses() {
        List<Course> courses = courseService.getAllInActivePeriod();

        if (courses == null || courses.isEmpty()) {
            return Collections.emptyList();
        }

        return CourseAssembler.toDto(courses);
    }

    @Override
    public void delete(List<Long> courseIds) {
        courseService.delete(courseIds);
    }
}
