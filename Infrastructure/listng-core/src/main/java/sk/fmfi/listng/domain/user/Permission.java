package sk.fmfi.listng.domain.user;

import sk.fmfi.listng.domain.enums.CourseRole;
import sk.fmfi.listng.domain.enums.CourseStatus;

/**
 * Trieda reprezentujúca študentské prístupové práva do kurzu.
 */

public class Permission {

    private long id;

    private long courseId;

    private CourseRole role;
    
    private CourseStatus status;
    
    private Long groupId;
    
    public Permission(long userId, long courseId, CourseRole role) {
        this.id = userId;
        this.courseId = courseId;
        this.role = role;
        this.status = CourseStatus.PENDING;
        this.groupId = -1L;
    }
    
    public Permission(long userId, long courseId, CourseRole role, CourseStatus status) {
        this.id = userId;
        this.courseId = courseId;
        this.role = role;
        this.status = status;
        this.groupId = -1L;
    }

    public Permission(long userId, long courseId, CourseRole role, CourseStatus status, long groupId) {
        this.id = userId;
        this.courseId = courseId;
        this.role = role;
        this.status = status;
        this.groupId = groupId;
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

    public CourseStatus getStatus() {
        return status;
    }

    public void setStatus(CourseStatus status) {
        this.status = status;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }
}
