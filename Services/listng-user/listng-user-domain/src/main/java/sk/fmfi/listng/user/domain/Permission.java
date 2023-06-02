package sk.fmfi.listng.user.domain;

import java.io.Serializable;

/**
 * Trieda reprezentujúca študentské prístupové práva do kurzu.
 */

public class Permission implements Serializable {

    private Long id;
    
    private long userId;

    private long courseId;

    private CourseRole role;
    
    private CourseStatus status;
    
    private Long groupId = null;
    
    public Permission(long userId, long courseId, CourseRole role) {
        this.userId = userId;
        this.courseId = courseId;
        this.role = role;
        this.status = CourseStatus.PENDING;
        this.groupId = -1L;
    }
    
    public Permission(long userId, long courseId, CourseRole role, CourseStatus status) {
        this.userId = userId;
        this.courseId = courseId;
        this.role = role;
        this.status = status;
        this.groupId = -1L;
    }

    public Permission(long userId, long courseId, CourseRole role, CourseStatus status, Long groupId) {
        this.userId = userId;
        this.courseId = courseId;
        this.role = role;
        this.status = status;
        this.groupId = groupId;
    }

    public Permission(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
