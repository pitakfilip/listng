package sk.fmfi.listng.rest.security.user.role;

import sk.fmfi.listng.domain.enums.CourseRole;

public class AppCourseRole implements UserRole {
    
    private Long courseId;
    
    private CourseRole role;

    public AppCourseRole(long courseId, String roleName) {
        this.courseId = courseId;
        this.role = CourseRole.valueOf(roleName);
    }

    @Override
    public Long getIdentifier() {
        return courseId;
    }
    
    public CourseRole getRole() {
        return role;
    }

    @Override
    public String getName() {
        return courseId + "/" + role.name();
    }
}
