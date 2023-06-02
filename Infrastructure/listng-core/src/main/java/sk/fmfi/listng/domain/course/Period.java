package sk.fmfi.listng.domain.course;

import sk.fmfi.listng.domain.administration.MultiLangText;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

/**
 * Trieda reprezentujúca vyučbové obdobie.
 */

public class Period implements Serializable {

    private long id;

    private MultiLangText name;

    private Date start;
    
    private Date end;
    
    private boolean active = false;

    public Period(MultiLangText name, Date start, Date end) {
        this.name = name;
        this.start = start;
        this.end = end;
    }

    @Deprecated
    public Period() {
        // Hibernate only        
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public MultiLangText getName() {
        return name;
    }

    public void setName(MultiLangText name) {
        this.name = name;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean overlaps(Period other){
        Date otherStart = other.getStart();
        Date otherEnd = other.getEnd();
        
        return isWithinPeriod(otherStart) || isWithinPeriod(otherEnd);
    }
    
    public boolean isWithinPeriod(Date day){
        return start.equals(day)
                || end.equals(day)
                || (start.before(day) && end.after(day)); 
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
