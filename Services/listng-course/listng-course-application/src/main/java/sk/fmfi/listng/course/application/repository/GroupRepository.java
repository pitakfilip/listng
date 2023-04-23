package sk.fmfi.listng.course.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.fmfi.listng.domain.administration.Group;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {
    
    List<Group> getGroupsByCourseId(Long courseId);
}
