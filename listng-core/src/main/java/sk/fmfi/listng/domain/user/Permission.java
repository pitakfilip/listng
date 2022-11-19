package sk.fmfi.listng.domain.user;

import sk.fmfi.listng.domain.administration.Group;
import sk.fmfi.listng.domain.course.Course;

/**
 * Trieda reprezentujúca prístupové práva do kurzu.
 * V prípade inej role ako STUDENT úživateľ nieje v skupinách.
 */

public class Permission {

    private Course course;

    private Role role;

    private Group group;

    public Permission(Course course, Role role) {
        this.course = course;
        this.role = role;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
