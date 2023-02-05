package sk.fmfi.listng.domain.course;

import java.util.Objects;

/**
 * Trieda reprezentujúca kurz v určenom období.
 */

public class Course {

    private Long id;
    private String name;

    private Period period;

    public Course(String name, Period period) {
        this.name = name;
        this.period = period;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return getName().equals(course.getName()) && getPeriod().equals(course.getPeriod());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPeriod());
    }
}
