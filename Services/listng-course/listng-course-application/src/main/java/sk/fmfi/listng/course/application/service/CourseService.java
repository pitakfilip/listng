package sk.fmfi.listng.course.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import sk.fmfi.listng.course.application.assembler.CourseAssembler;
import sk.fmfi.listng.course.application.repository.CourseRepository;
import sk.fmfi.listng.course.application.repository.GroupRepository;
import sk.fmfi.listng.course.domain.Course;
import sk.fmfi.listng.course.domain.Group;
import sk.fmfi.listng.course.dto.CourseDto;
import sk.fmfi.listng.course.dto.GroupDto;
import sk.fmfi.listng.infrastructure.common.dto.PageResponse;
import sk.fmfi.listng.infrastructure.common.dto.PagingParams;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private PeriodService periodService;

    @Autowired
    private GroupRepository groupRepository;

    public boolean courseExists(long courseId) {
        return courseRepository.existsById(courseId);
    }
    
    public void saveCourse(CourseDto dto) {
        Course course = CourseAssembler.fromDto(dto);
        if (course.getId() == 0) {
            course.setId(null);
        }
        
        for(Group group: course.getGroups()) {
            group.setCourseId(course.getId());
            
            if (group.getId() < 1) {
                group.setId(null);
            }
        }
        
        courseRepository.save(course);
    }

    /**
     * Copy an existing Course into a new specific Period. Copies the course configuration (Course object and its groups),
     * excluding classes, as there may be a collision with existing classes in the given period.
     * <pre><strong>
     * TODO</strong> copy tasksets of the given existing course with their configurations,
     *  but without Date/Time related data (e.g. deadline) -> Need a TaskSetApi proxy in REST once implemented.
     * </pre>
     * @param courseIds ids of existing courses.
     * @param periodId id of period to which we copy the course.
     */
    public void copyCourses(List<Long> courseIds, Long periodId) {
        List<Course> courses = courseRepository.findAllById(courseIds);
        
        List<Course> copies = courses.stream()
                .map(course -> new Course(course, periodId))
                .toList();
        
        courseRepository.saveAll(copies);
    }

    public Optional<Course> getById(Long courseId) {
        return courseRepository.findById(courseId);
    }

    public PageResponse<CourseDto> getCoursePage(PagingParams params) {
        Page<Course> page = courseRepository.findAll(params.compile());
        return new PageResponse<>(page, CourseAssembler.toDto(page.getContent()));
    }
    
    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    public List<Course> getAllInPeriod(long periodId) {
        return courseRepository.findByPeriodId(periodId);
    }

    public List<Course> getAllInActivePeriod() {
        Long periodId = periodService.getActivePeriodId();

        if (periodId == null) {
            return null;
        }

        return courseRepository.findByPeriodId(periodId);
    }

    public void delete(List<Long> courseIds) {
        courseRepository.deleteAllById(courseIds);
    }
}
