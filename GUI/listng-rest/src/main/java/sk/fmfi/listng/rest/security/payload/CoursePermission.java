package sk.fmfi.listng.rest.security.payload;

import sk.fmfi.listng.user.enums.CourseRole;

public class CoursePermission {
    
    private Long courseId;
    
    private CourseRole permission;

    public CoursePermission(Long courseId, CourseRole permission) {
        this.courseId = courseId;
        this.permission = permission;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public CourseRole getPermission() {
        return permission;
    }

    public void setPermission(CourseRole permission) {
        this.permission = permission;
    }
}
