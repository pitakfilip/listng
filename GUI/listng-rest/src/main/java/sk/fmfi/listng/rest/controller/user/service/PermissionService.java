package sk.fmfi.listng.rest.controller.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.fmfi.listng.course.dto.CourseDto;
import sk.fmfi.listng.rest.controller.course.service.CourseService;
import sk.fmfi.listng.rest.proxy.user.UserApiProxy;
import sk.fmfi.listng.rest.proxy.user.UserPermissionApiProxy;
import sk.fmfi.listng.user.dto.PermissionDto;
import sk.fmfi.listng.user.dto.UserDto;
import sk.fmfi.listng.user.enums.CourseRole;
import sk.fmfi.listng.user.enums.CourseStatus;
import sk.fmfi.listng.user.enums.SystemRole;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PermissionService {

    @Autowired
    private UserApiProxy userApi;
    
    @Autowired
    private UserPermissionApiProxy permissionApi;
    
    @Autowired 
    private CourseService courseService;

    
    public List<PermissionDto> getPermissions(Long userId) {
        return userApi.getUserById(userId).permissions;
    }
    
    // Pre ucitelov chceme nastavit default pristup do kurzu (VIEWER) + spojit s jeho existujucimi pravami
    public List<PermissionDto> getPermissionsInPeriod(Long userId, Long periodId) {
        List<CourseDto> courses = courseService.getCoursesInPeriod(periodId);
        Set<Long> courseIds = courses.stream()
                .map(course -> course.id)
                .collect(Collectors.toSet());

        UserDto user = userApi.getUserById(userId);
        List<PermissionDto> result = new ArrayList<>();

        user.permissions.forEach(permission -> {
            if (courseIds.contains(permission.courseId)) {
                result.add(permission);
                courseIds.remove(permission.courseId);
            }
        });

        if (!user.role.equals(SystemRole.STUDENT.name())) {
            for (Long courseId : courseIds) {
                PermissionDto dto = new PermissionDto();
                dto.userId = userId;
                dto.courseId = courseId;
                dto.role = CourseRole.VIEWER;
                dto.status = CourseStatus.ACTIVE;
                result.add(dto);
            }
        }

        return result;
    }

    public void requestCourseEntry(Long userId, Long courseId) {
        permissionApi.requestAccessToCourse(userId, courseId);
    }
}
