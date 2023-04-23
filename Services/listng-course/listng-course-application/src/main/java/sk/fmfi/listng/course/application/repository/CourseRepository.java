package sk.fmfi.listng.course.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.fmfi.listng.domain.course.Course;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
 
    List<Course> findByPeriodId(long periodId);
}
