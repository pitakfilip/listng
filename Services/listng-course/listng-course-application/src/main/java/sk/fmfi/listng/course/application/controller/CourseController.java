package sk.fmfi.listng.course.application.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sk.fmfi.listng.course.api.CourseApi;
import sk.fmfi.listng.course.application.assembler.CourseAssembler;
import sk.fmfi.listng.course.application.service.*;
import sk.fmfi.listng.course.domain.Course;
import sk.fmfi.listng.course.domain.Period;
import sk.fmfi.listng.course.dto.CourseDto;

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

    @Autowired
    private GroupService groupService;
    
    @Override
    public void create(CourseDto dto) {
        if (periodService.getPeriod(dto.periodId).isEmpty()) {
            throw new EntityNotFoundException("error.period.not.found");
        }
        
        Long courseId = courseService.saveCourse(dto);
        groupService.createDefaultGroup(courseId);
    }

    @Override
    public void copyCourse(Long courseId, Long periodId) {
        courseService.copyCourse(courseId, periodId);        
    }

    @Override
    public List<CourseDto> getCourses() {
        return courseService.getAll().stream()
                .map(CourseAssembler::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseDto> getCoursesOfPeriod(@RequestParam Long periodId) {
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
    public CourseDto getCourseById(Long courseId) {
        if (!courseService.courseExists(courseId)) {
            throw new EntityNotFoundException("error.course.not.found");
        }
        Optional<Course> course = courseService.getById(courseId);
        
        return CourseAssembler.toDto(course.get());
    }

    @Override
    public void update(CourseDto course) {
        Optional<Course> fromCourse = courseService.getById(course.id);
        if (fromCourse.isEmpty()) {
            throw new EntityNotFoundException("error.course.not.found");
        }
        courseService.saveCourse(course);
    }

    @Override
    public void delete(Long courseId) {
        Optional<Course> fromCourse = courseService.getById(courseId);
        if (fromCourse.isEmpty()) {
            throw new EntityNotFoundException("error.course.not.found");
        }
        courseService.delete(courseId);
    }
}
