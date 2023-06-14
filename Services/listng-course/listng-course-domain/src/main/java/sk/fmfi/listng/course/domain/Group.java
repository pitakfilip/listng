package sk.fmfi.listng.course.domain;


import sk.fmfi.listng.infrastructure.common.MultiLangText;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Trieda reprezentujúca skupinu študentov v kurze.
 */

public class Group implements Serializable {
    
    private Long id;
    
    private Long courseId;

    private boolean defaultGroup;
    
    private Integer capacity;
    
    private MultiLangText name;
    
//    private Set<Class> classes = new HashSet<>();

    @Deprecated
    public Group() {
        // Hibernate only
    }

    public Group(long courseId, MultiLangText name) {
        this.courseId = courseId;
        this.name = name;
    }

    public Group copy() {
        Group copy = new Group();
        copy.setDefaultGroup(defaultGroup);
        copy.setCapacity(capacity);
        copy.setName(new MultiLangText(name.getSK(), name.getEN()));
        return copy;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long course) {
        this.courseId = course;
    }

    public boolean isDefaultGroup() {
        return defaultGroup;
    }

    public void setDefaultGroup(boolean defaultGroup) {
        this.defaultGroup = defaultGroup;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public MultiLangText getName() {
        return name;
    }

    public void setName(MultiLangText name) {
        this.name = name;
    }

//    public Set<Class> getClasses() {
//        return classes;
//    }
//
//    public void setClasses(Set<Class> classes) {
//        this.classes = classes;
//    }
}
