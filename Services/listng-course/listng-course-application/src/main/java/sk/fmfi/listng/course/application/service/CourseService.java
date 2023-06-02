package sk.fmfi.listng.course.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.fmfi.listng.course.application.assembler.CourseAssembler;
import sk.fmfi.listng.course.application.assembler.PeriodAssembler;
import sk.fmfi.listng.course.application.repository.CourseRepository;
import sk.fmfi.listng.course.application.repository.GroupRepository;
import sk.fmfi.listng.course.domain.Course;
import sk.fmfi.listng.course.domain.Group;
import sk.fmfi.listng.course.domain.Period;
import sk.fmfi.listng.course.dto.CourseDto;
import sk.fmfi.listng.course.dto.PeriodDto;
import sk.fmfi.listng.infrastructure.common.dto.MultiLangText;

import java.util.List;
import java.util.Optional;

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
    
    public Long saveCourse(CourseDto dto) {
        Course saved = courseRepository.save(CourseAssembler.fromDto(dto));
        return saved.getId();
    }

    /**
     * Copy an existing Course into a new specific Period. Copies the course configuration (Course object and its groups),
     * excluding classes, as there may be a collision with existing classes in the given period.
     * <pre><strong>
     * TODO</strong> copy tasksets of the given existing course with their configurations,
     *  but without Date/Time related data (e.g. deadline) -> Need a TaskSetApi proxy once implemented.
     * </pre>
     * @param courseId id of existing course.
     * @param periodId id of period to which we copy the course.
     */
    public void copyCourse(Long courseId, Long periodId) {
        Course from = courseRepository.getReferenceById(courseId);       
        
        if (!periodService.exists(periodId)) {
            return;
        }
        
        Course newCourse = Course.copy(from, periodId);
        Course course = courseRepository.save(newCourse);

        List<Group> oldGroups = groupRepository.getGroupsByCourseId(from.getId());
        if (oldGroups.isEmpty()) {
            return;
        }

        for (Group oldGroup : oldGroups) {
            groupRepository.save(new Group(course.getId(), new MultiLangText(oldGroup.getName())));
        }
    }

    public Optional<Course> getById(Long courseId) {
        return courseRepository.findById(courseId);
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

    public void delete(Long courseId) {
        courseRepository.deleteById(courseId);
    }
}
