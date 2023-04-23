package sk.fmfi.listng.course.application.controller;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sk.fmfi.listng.course.api.CourseApi;
import sk.fmfi.listng.course.application.assembler.CourseAssembler;
import sk.fmfi.listng.course.application.service.*;
import sk.fmfi.listng.course.dto.CourseDto;
import sk.fmfi.listng.domain.course.Course;
import sk.fmfi.listng.domain.course.Period;
import sk.fmfi.listng.infrastructure.common.BaseController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class CourseController extends BaseController implements CourseApi {

    @Autowired
    private CourseService courseService;

    @Autowired
    private PeriodService periodService;

    @Autowired
    private GroupService groupService;
    
    @Override
    public void create(CourseDto dto) throws NotFoundException {
        if (periodService.getPeriod(dto.periodId).isEmpty()) {
            throw new NotFoundException("error.period.not.found");
        }
        
        Course course = CourseAssembler.fromDto(dto);
        courseService.saveCourse(course);
        groupService.createDefaultGroup(course);
    }

    @Override
    public void copyCourse(Long courseId, Long periodId) throws NotFoundException {
        Optional<Period> targetPeriod = periodService.getPeriod(periodId);
        if (targetPeriod.isEmpty()) {
            throw new NotFoundException("error.period.not.found");
        }
        Optional<Course> fromCourse = courseService.getById(courseId);
        if (fromCourse.isEmpty()) {
            throw new NotFoundException("error.course.not.found");
        }
        courseService.copyCourse(fromCourse.get(), targetPeriod.get());        
    }

    @Override
    public List<CourseDto> getCourses() {
        return courseService.getAll().stream()
                .map(CourseAssembler::toDto)
                .toList();
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
    public List<CourseDto> getActiveCourses() throws NotFoundException {
        List<Course> courses = courseService.getAllInActivePeriod();

        if (courses == null || courses.isEmpty()) {
            return Collections.emptyList();
        }

        return CourseAssembler.toDto(courses);
    }

    @Override
    public CourseDto getCourseById(Long courseId) throws NotFoundException {
        if (!courseService.courseExists(courseId)) {
            throw new NotFoundException("error.course.not.found");
        }
        Optional<Course> course = courseService.getById(courseId);
        
        return CourseAssembler.toDto(course.get());
    }

    @Override
    public void update(CourseDto course) throws NotFoundException {
        Optional<Course> fromCourse = courseService.getById(course.id);
        if (fromCourse.isEmpty()) {
            throw new NotFoundException("error.course.not.found");
        }
        courseService.saveCourse(CourseAssembler.fromDto(course));
    }

    @Override
    public void delete(Long courseId) throws NotFoundException {
        Optional<Course> fromCourse = courseService.getById(courseId);
        if (fromCourse.isEmpty()) {
            throw new NotFoundException("error.course.not.found");
        }
        courseService.delete(courseId);
    }
}
