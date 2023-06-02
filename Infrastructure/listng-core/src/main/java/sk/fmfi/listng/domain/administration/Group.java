package sk.fmfi.listng.domain.administration;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Trieda reprezentujúca skupinu študentov v kurze.
 */

public class Group implements Serializable {
    
    private long id;
    
    private long courseId;

    private MultiLangText name;
    
    private Set<Class> classes = new HashSet<>();

    @Deprecated
    public Group() {
        // Hibernate only
    }

    public Group(long course, MultiLangText name) {
        this.courseId = course;
        this.name = name;
    }

    public Group(long course, String sk, String en) {
        this.courseId = course;
        this.name = new MultiLangText(sk, en);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long course) {
        this.courseId = course;
    }

    public MultiLangText getName() {
        return name;
    }

    public void setName(MultiLangText name) {
        this.name = name;
    }

    public Set<Class> getClasses() {
        return classes;
    }

    public void setClasses(Set<Class> classes) {
        this.classes = classes;
    }
}
