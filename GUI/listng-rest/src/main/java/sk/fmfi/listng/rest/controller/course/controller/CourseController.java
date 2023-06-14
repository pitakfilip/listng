package sk.fmfi.listng.rest.controller.course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.fmfi.listng.course.dto.CourseDto;
import sk.fmfi.listng.infrastructure.common.Response;
import sk.fmfi.listng.rest.common.ListController;
import sk.fmfi.listng.rest.controller.course.service.CourseService;
import sk.fmfi.listng.rest.dto.CourseBaseDto;

import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping("/course")
public class CourseController extends ListController {
    
    @Autowired
    private CourseService courseService;
    
    @GetMapping("/{periodId}/period")
    public Response<List<CourseBaseDto>> getCoursesInPeriod(@PathVariable Long periodId) {
        List<CourseDto> courses = courseService.getCoursesInPeriod(periodId);

        if (courses == null) {
            return success(Collections.emptyList());
        }
        return success(CourseBaseDto.of(courses));
    }
    
//    @GetMapping("/courses")
//    public Response<List<CourseBaseDto>> getAllCourses() {
//        List<CourseDto> courses = courseService.getAllCourses();
//
//        if (courses == null) {
//            return success(Collections.emptyList());
//        }
//        return success(CourseBaseDto.of(courses));
//    }
}
