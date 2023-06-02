package sk.fmfi.listng.user.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.fmfi.listng.user.domain.Permission;

import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    
    Permission findByUserIdAndCourseId(Long userId, Long courseId);
    
    List<Permission> findAllByUserIdInAndCourseId(List<Long> userIds, Long courseId);

    void deleteAllByUserId(Long userId);
    
    void deleteByUserIdAndCourseId(Long userId, Long courseId);

    void deleteByUserIdInAndCourseId(List<Long> userIds, Long courseId);
    
}
