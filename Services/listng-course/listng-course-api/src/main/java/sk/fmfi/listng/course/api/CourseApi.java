package sk.fmfi.listng.course.api;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import sk.fmfi.listng.course.dto.CourseDto;

public interface CourseApi {
    
    @PostMapping(value = "/new")
    void create(@RequestBody CourseDto course);
    
    @GetMapping(value = "/copy")
    void copyCourse(@RequestParam Long courseId, @RequestParam Long periodId);
    
    @GetMapping(value = "/all")
    List<CourseDto> getCourses();
    
    @GetMapping(value = "/period")
    List<CourseDto> getCoursesOfPeriod(@RequestParam Long periodId);

    @GetMapping(value = "/active")
    List<CourseDto> getActiveCourses();
    
    @GetMapping(value = "/get")
    CourseDto getCourseById(@RequestParam Long courseId);
    
    @PostMapping(value = "/update")
    void update(@RequestBody CourseDto course);
    
    @DeleteMapping(value = "/delete")
    void delete(@RequestParam Long courseId);
    
}
