package sk.fmfi.listng.course.domain;

import sk.fmfi.listng.infrastructure.common.MultiLangText;

import java.io.Serializable;
import java.sql.Array;
import java.util.*;

/**
 * Trieda reprezentujúca kurz v určenom období.
 */
public class Course implements Serializable {

    private Long id;

    private MultiLangText name;

    private MultiLangText abbreviation;

    private long periodId;
    
    private Set<Group> groups = new HashSet<>();
    
    public long getPeriodId() {
        return periodId;
    }

    public void setPeriodId(long periodId) {
        this.periodId = periodId;
    }

    @Deprecated
    public Course() {
        // Hibernate only
    }

    public Course(MultiLangText name, long periodId) {
        this.name = name;
        this.periodId = periodId;
    }

    public Course(Course from, long periodId) {
        this.name = new MultiLangText(from.name.getSK(), from.name.getEN());
        this.abbreviation = new MultiLangText(from.abbreviation.getSK(), from.abbreviation.getEN());
        this.periodId = periodId;
        Set<Group> groupCopies = new HashSet<>();
        for (Group group : from.getGroups()){
            groupCopies.add(group.copy());
        }
        this.groups = groupCopies;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MultiLangText getName() {
        return name;
    }

    public void setName(MultiLangText name) {
        this.name = name;
    }

    public MultiLangText getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(MultiLangText abbreviation) {
        this.abbreviation = abbreviation;
    }

    public Long getPeriod() {
        return periodId;
    }

    public void setPeriod(Long periodId) {
        this.periodId = periodId;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    public void addGroup(Group group) {
        this.groups.add(group);
    }
    
    public void addGroups(Set<Group> groups){
        this.groups.addAll(groups);
    }

    public void clearGroups() {this.groups.clear();}
    
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
