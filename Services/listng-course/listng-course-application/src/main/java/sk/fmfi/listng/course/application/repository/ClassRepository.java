package sk.fmfi.listng.course.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.fmfi.listng.domain.administration.Class;

import java.util.List;

public interface ClassRepository extends JpaRepository<Class, Long> {
    
    List<Class> getClassesByGroupId(Long groupId);
    
    List<Class> getClassesByRoom_Id(Long roomId);
}
