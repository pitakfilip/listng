package sk.fmfi.listng.domain.user;

import sk.fmfi.listng.domain.course.*;

/**
 * Trieda reprezentujúca študentské prístupové práva do kurzu.
 */

public class Permission {

    private Course course;

    private CourseRole role;

    public Permission(Course course, CourseRole role) {
        this.course = course;
        this.role = role;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public CourseRole getRole() {
        return role;
    }

    public void setRole(CourseRole role) {
        this.role = role;
    }

}
