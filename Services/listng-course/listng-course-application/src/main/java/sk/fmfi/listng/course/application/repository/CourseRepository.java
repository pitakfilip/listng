package sk.fmfi.listng.course.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.fmfi.listng.domain.course.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
