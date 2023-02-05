package sk.fmfi.listng.course.entity;

import sk.fmfi.listng.infrastructure.common.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CourseEntity extends BaseEntity {
    @Id
    @Column(name = "course_id", nullable = false)
    private Long id;


    public Long getId() {
        return id;
    }
}
