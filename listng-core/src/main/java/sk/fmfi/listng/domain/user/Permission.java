package sk.fmfi.listng.domain.user;

import sk.fmfi.listng.domain.enums.CourseRole;

/**
 * Trieda reprezentujúca študentské prístupové práva do kurzu.
 */

public class Permission {

    private long id;

    private long courseId;

    private CourseRole role;

    public Permission(long userId, long courseId, CourseRole role) {
        this.id = userId;
        this.courseId = courseId;
        this.role = role;
    }

    public Permission(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public CourseRole getRole() {
        return role;
    }

    public void setRole(CourseRole role) {
        this.role = role;
    }

}
