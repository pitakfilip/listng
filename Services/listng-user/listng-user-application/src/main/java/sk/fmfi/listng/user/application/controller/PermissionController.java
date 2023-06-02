package sk.fmfi.listng.user.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import sk.fmfi.listng.user.domain.CourseRole;
import sk.fmfi.listng.user.domain.CourseStatus;
import sk.fmfi.listng.user.api.UserPermissionApi;
import sk.fmfi.listng.user.application.service.PermissionService;

import java.util.List;

@RestController
public class PermissionController implements UserPermissionApi {

    @Autowired
    private PermissionService permissionService;
    
    
    @Override
    public void requestAccessToCourse(Long userId, Long courseId) {
        permissionService.createNewAccess(userId, courseId);
    }

    @Override
    public void setUserRole(Long userId, Long courseId, String role) {
        permissionService.setCourseAccess(userId, courseId, CourseRole.valueOf(role), null);
    }

    @Override
    public void setUsersRole(List<Long> userIds, Long courseId, String role) {
        permissionService.setCourseAccessBulk(userIds, courseId, CourseRole.valueOf(role), null);
    }

    @Override
    public void setUserStatus(Long userId, Long courseId, String status) {
        permissionService.setCourseAccess(userId, courseId, null,CourseStatus.valueOf(status));
    }

    @Override
    public void setUsersStatus(List<Long> userIds, Long courseId, String status) {
        permissionService.setCourseAccessBulk(userIds, courseId, null,CourseStatus.valueOf(status));
    }

    @Override
    public void setUserPermission(Long userId, Long courseId, String role, String status) {
        permissionService.setCourseAccess(userId, courseId, CourseRole.valueOf(role), CourseStatus.valueOf(status));
    }

    @Override
    public void setUsersPermission(List<Long> userIds, Long courseId, String role, String status) {
        permissionService.setCourseAccessBulk(userIds, courseId, CourseRole.valueOf(role), CourseStatus.valueOf(status));
    }

    @Override
    public void setGroupToUsers(List<Long> userIds, Long courseId, Long groupId) {
        permissionService.setCourseGroup(userIds, courseId, groupId);
    }

    @Override
    public void deletePermission(Long userId, Long courseId) {
        permissionService.deleteCourseAccess(userId, courseId);
    }

    @Override
    public void deletePermissions(List<Long> userIds, Long courseId) {
        permissionService.deleteCourseAccessBulk(userIds, courseId);
    }


}
