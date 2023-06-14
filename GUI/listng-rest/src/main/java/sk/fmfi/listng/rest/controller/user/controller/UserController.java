package sk.fmfi.listng.rest.controller.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.fmfi.listng.infrastructure.common.Response;
import sk.fmfi.listng.rest.common.ListController;
import sk.fmfi.listng.rest.common.menu.HeaderItems;
import sk.fmfi.listng.rest.controller.user.service.PermissionService;
import sk.fmfi.listng.rest.controller.user.service.UserService;
import sk.fmfi.listng.user.dto.PermissionDto;

import java.util.List;

/**
 * Public controller for handling requests for Users and Permissions.
 * Used only for getting the users permissions and handling requests to join a course.
 */
@RestController
@RequestMapping("/user")
public class UserController extends ListController {

    @Autowired
    private PermissionService permissionService;
    
    /**
     * Get course permissions within period for requesting user.
     * @param periodId
     * @return List collection of found permissions.
     */
    @GetMapping("/period/{periodId}/permissions")
    public Response<List<PermissionDto>> getPermissionsInPeriod(@PathVariable Long periodId) {
        return success(permissionService.getPermissionsInPeriod(getUserId(), periodId));
    }

    /**
     * Request new permission to course for student user.
     * @param courseId
     */
    @GetMapping("/{courseId}/request")
    public void requestCourseEntry(@PathVariable Long courseId) {
        Long userId = getUserId();
        permissionService.requestCourseEntry(userId, courseId);
    }
    
    @GetMapping("/isAdmin")
    public Response<Boolean> isLoggedUserAdmin() {
        Long userId = getUserId();
        if (userId == null) {
            return error();
        }
        
        return success(isRoot());
    }
    
    @GetMapping("/header")
    public Response<List<HeaderItems>> getHeaderItems() {
        if (isStudent()) {
            return success();
        }
        if (isTeacher()) {
            return success(List.of(HeaderItems.USERS, HeaderItems.COURSES, HeaderItems.TASKS, HeaderItems.TASKSETS,
                    HeaderItems.OTHER, HeaderItems.MOSS, HeaderItems.LOGS));
        }
        return success(List.of(HeaderItems.USERS, HeaderItems.COURSES, HeaderItems.TASKS, HeaderItems.TASKSETS,
                HeaderItems.OTHER, HeaderItems.MOSS, HeaderItems.LOGS, HeaderItems.CONFIG));
    }
}
