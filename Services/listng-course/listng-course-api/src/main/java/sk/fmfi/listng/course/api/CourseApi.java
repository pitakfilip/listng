package sk.fmfi.listng.course.api;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import sk.fmfi.listng.course.dto.CourseDto;
import sk.fmfi.listng.infrastructure.common.dto.PageResponse;
import sk.fmfi.listng.infrastructure.common.dto.PagingParams;

public interface CourseApi {

    @PostMapping(value = "/save")
    void save(@RequestBody CourseDto course);

    @PostMapping(value = "/copy/{periodId}")
    void copyCourses(@RequestBody List<Long> courseIds, @PathVariable Long periodId);

    @GetMapping(value = "/{courseId}")
    CourseDto getCourseById(@PathVariable Long courseId);

    @PostMapping(value = "/page")
    PageResponse<CourseDto> getCoursePage(@RequestBody PagingParams page);

    @GetMapping(value = "/all")
    List<CourseDto> getCourses();

    @GetMapping(value = "/period/{periodId}")
    List<CourseDto> getCoursesOfPeriod(@PathVariable Long periodId);

    @GetMapping(value = "/active")
    List<CourseDto> getActiveCourses();

    @PostMapping(value = "/delete")
    void delete(@RequestBody List<Long> courseIds);
}
