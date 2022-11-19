package sk.fmfi.listng.domain.course;

import java.util.Date;
import java.util.Objects;

/**
 * Trieda reprezentujúca vyučbové obdobie.
 */

public class Period {

    private String name;

    private Date start;

    public Period(String name, Date start) {
        this.name = name;
        this.start = start;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Period period = (Period) o;
        return getName().equals(period.getName()) && getStart().equals(period.getStart());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getStart());
    }
}
