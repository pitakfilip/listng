package sk.fmfi.listng.rest.controller.course.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.fmfi.listng.course.dto.CourseDto;
import sk.fmfi.listng.rest.proxy.course.CourseApiProxy;
import sk.fmfi.listng.rest.proxy.course.PeriodApiProxy;

import java.util.List;

@Service
public class CourseService {
    
    @Autowired
    private CourseApiProxy courseApi;
    
    @Autowired
    private PeriodApiProxy periodApi;
    
    
    public List<CourseDto> getCoursesInPeriod(Long periodId) {
        return courseApi.getCoursesOfPeriod(periodId);
    }
    
    public List<CourseDto> getAllCourses() {
        return courseApi.getCourses();
    }
    
    public void requestCourseEntry(Long userId, Long courseId) {
        
    }
    
    
}
