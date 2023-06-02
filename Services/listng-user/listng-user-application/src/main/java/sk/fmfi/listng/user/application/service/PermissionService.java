package sk.fmfi.listng.user.application.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.fmfi.listng.user.domain.CourseRole;
import sk.fmfi.listng.user.domain.CourseStatus;
import sk.fmfi.listng.user.domain.Permission;
import sk.fmfi.listng.user.application.repository.PermissionRepository;

import java.util.List;

@Service
public class PermissionService {

    @Autowired
    private UserService userService;
    
    @Autowired
    private PermissionRepository repository;
    
    public void createNewAccess(Long userId, Long courseId) {
        if (!userService.exists(userId)) {
            throw new EntityNotFoundException("error.user.not.found");
        }
        
        if (repository.findByUserIdAndCourseId(userId, courseId) != null) {
            return; //Uz ma vytvoreny pristup
        }
        
        Permission permission = new Permission(userId, courseId, CourseRole.NOT_ASSIGNED, CourseStatus.PENDING);
        repository.save(permission);
    }
    
    // Create + Save pristup do kurzu pre single user
    public void setCourseAccess(Long userId, Long courseId, CourseRole role, CourseStatus status) {
        if ((role == null && status == null)) {
            return; // Nevalidne pouzitie
        }
        
        if (!userService.exists(userId)) {
            throw new EntityNotFoundException("error.user.not.found");
        }
        
        Permission permission = repository.findByUserIdAndCourseId(userId, courseId);
        if (permission == null) {
            permission = new Permission(userId, courseId, role, status);
        }
        
        if (role != null) {
            permission.setRole(role);
        }
        if (status != null) {
            permission.setStatus(status);
        }
        repository.save(permission);
    }

    // Create + Save pristup do kurzu pre viacero users
    public void setCourseAccessBulk(List<Long> userIds, Long courseId, CourseRole role, CourseStatus status) {
        if (role == null && status == null) {
            return; // Nevalidne pouzitie
        }
        
        for (Long userId : userIds) {
            if (!userService.exists(userId)) {
                throw new EntityNotFoundException("error.user.not.found");
            }
        }
        
        List<Permission> permissions = repository.findAllByUserIdInAndCourseId(userIds, courseId);
        for (Permission permission : permissions) {
            if (role != null) {
                permission.setRole(role);
            }
            if (status != null) {
                permission.setStatus(status);
            }
        }
        repository.saveAll(permissions);
    }

    public void setCourseGroup(List<Long> userIds, Long courseId, Long groupId) {
        for (Long userId : userIds) {
            if (!userService.exists(userId)) {
                throw new EntityNotFoundException("error.user.not.found");
            }
        }

        List<Permission> permissions = repository.findAllByUserIdInAndCourseId(userIds, courseId);
        for (Permission permission : permissions) {
            permission.setGroupId(groupId);
        }
        
        repository.saveAll(permissions);
    }
    
    public void deleteCourseAccess(Long userId, Long courseId) {
        repository.deleteByUserIdAndCourseId(userId, courseId);
    }

    public void deleteCourseAccessBulk(List<Long> userIds, Long courseId) {
        repository.deleteByUserIdInAndCourseId(userIds, courseId);
    }
    
}
