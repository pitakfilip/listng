package sk.fmfi.listng.rest.controller.course.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.fmfi.listng.course.dto.CourseDto;
import sk.fmfi.listng.infrastructure.common.Response;
import sk.fmfi.listng.infrastructure.common.dto.PageResponse;
import sk.fmfi.listng.infrastructure.common.dto.PagingParams;
import sk.fmfi.listng.rest.common.ListController;
import sk.fmfi.listng.rest.controller.course.service.CourseService;

import java.util.List;

@RestController
@RequestMapping("/admin/course")
public class CourseAdminController extends ListController {
    
    @Autowired
    private CourseService courseService;

    @PostMapping("/page")
    public Response<PageResponse<CourseDto>> getCoursePage(@RequestBody PagingParams paging) {
        PageResponse<CourseDto> page = courseService.getCoursePage(paging);
        return success(page);
    }
    
    @PostMapping("/save")
    public Response saveCourse(@RequestBody CourseDto courseDto) {
        courseService.save(courseDto);
        return success();
    }
    
    @DeleteMapping("/{courseId}/delete")
    public Response deleteCourse(@PathVariable Long courseId) {
        courseService.delete(List.of(courseId));
        return success();
    }
    
    @PostMapping("/bulk/delete")
    public Response deleteBulk(@RequestBody List<Long> courseIds) {
        courseService.delete(courseIds);
        return success();
    }
    
    @PostMapping("/copy")
    public void copyCourses(@RequestBody List<Long> courseIds, Long periodId) {
        courseService.copyCourses(courseIds, periodId);
    }
}
