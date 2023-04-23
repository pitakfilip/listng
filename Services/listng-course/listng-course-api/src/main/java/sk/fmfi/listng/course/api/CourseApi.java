package sk.fmfi.listng.course.api;

import javassist.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import sk.fmfi.listng.course.dto.CourseDto;

public interface CourseApi {
    
    @PostMapping(value = "/course/new")
    void create(@RequestBody CourseDto course) throws NotFoundException;
    
    @GetMapping(value = "/course/copy")
    void copyCourse(@RequestParam Long courseId, @RequestParam Long periodId) throws NotFoundException;
    
    @GetMapping(value = "/courses/all")
    List<CourseDto> getCourses();
    
    @GetMapping(value = "/courses/period")
    List<CourseDto> getCoursesOfPeriod(@RequestParam Long periodId);

    @GetMapping(value = "/courses/active")
    List<CourseDto> getActiveCourses() throws NotFoundException;
    
    @GetMapping(value = "/course/get")
    CourseDto getCourseById(@RequestParam Long courseId) throws NotFoundException;
    
    @PostMapping(value = "/course/update")
    void update(@RequestBody CourseDto course) throws NotFoundException;
    
    @DeleteMapping(value = "/course/delete")
    void delete(@RequestParam Long courseId) throws NotFoundException;
    
}
