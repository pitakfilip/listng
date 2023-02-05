package sk.fmfi.listng.course.entity;

import sk.fmfi.listng.infrastructure.common.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class PeriodEntity extends BaseEntity {
    @Id
    @Column(name = "period_id", nullable = false)
    private Long id;

    private String name;

    private Date start;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }
}
