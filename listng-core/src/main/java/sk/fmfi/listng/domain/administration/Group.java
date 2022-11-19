package sk.fmfi.listng.domain.administration;

import sk.fmfi.listng.domain.course.Course;

/**
 * Trieda reprezentujúca skupinu študentov v kurze.
 */

public class Group {

    private Course course;

    private String name;

    public Group(Course course, String name) {
        this.course = course;
        this.name = name;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
