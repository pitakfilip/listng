package sk.fmfi.listng.user.entity;

import sk.fmfi.listng.infrastructure.common.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "permission")
@IdClass(PermissionEntity.class)
public class PermissionEntity extends BaseEntity {

    @Id
    @JoinColumn(name = "user_id", nullable = false)
    private Long userId;

    private Long courseId;

    private String role;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
