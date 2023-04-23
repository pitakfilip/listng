package sk.fmfi.listng.course.application.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.fmfi.listng.course.application.repository.CourseRepository;
import sk.fmfi.listng.course.application.repository.GroupRepository;
import sk.fmfi.listng.domain.administration.Group;
import sk.fmfi.listng.domain.administration.MultiLangText;
import sk.fmfi.listng.domain.course.Course;
import sk.fmfi.listng.domain.course.Period;

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
    
    public void saveCourse(Course course) {
        courseRepository.save(course);
    }

    /**
     * Copy an existing Course into a new specific Period. Copies the course configuration (Course object and its groups),
     * excluding classes, as there may be a collision with existing classes in the given period.
     * <pre><strong>
     * TODO</strong> copy tasksets of the given existing course with their configurations,
     *  but without Date/Time related data (e.g. deadline) -> Need a TaskSetApi proxy once implemented.
     * </pre>
     * @param from existing course which we want to copy.
     * @param period new period to which we copy an existing course.
     */
    public void copyCourse(Course from, Period period) {
        Course newCourse = Course.copy(from, period.getId());
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

    public List<Course> getAllInActivePeriod() throws NotFoundException {
        Long periodId = periodService.getActivePeriodId();

        if (periodId == null) {
            throw new NotFoundException("error.period.active.not.set");
        }

        return courseRepository.findByPeriodId(periodId);
    }

    public void delete(Long courseId) {
        courseRepository.deleteById(courseId);
    }
}
