package sk.fmfi.listng.course.domain;

import sk.fmfi.listng.infrastructure.common.dto.MultiLangText;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Trieda reprezentujúca kurz v určenom období.
 */
public class Course implements Serializable {

    private Long id;

    private MultiLangText name;

    private MultiLangText abbreviation;

    private long periodId;
    
    private List<Group> groups = new ArrayList<>();
    
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

    public static Course copy(Course from, long periodId) {
        Course course = new Course(new MultiLangText(from.name.getSK(), from.name.getEN()), periodId);
        course.abbreviation = new MultiLangText(from.abbreviation.getSK(), from.abbreviation.getEN());
        
        return course;
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

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = new ArrayList<>(groups);
    }

    public void addGroup(Group group) {
        this.groups.add(group);
    }
    
    public void addGroups(List<Group> groups){
        this.groups.addAll(groups);
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
