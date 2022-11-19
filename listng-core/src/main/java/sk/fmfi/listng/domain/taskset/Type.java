package sk.fmfi.listng.domain.taskset;

import sk.fmfi.listng.domain.course.Course;

public class Type {

    private Course course;

    private String name;

    private boolean uploads;

    public Type(Course course, String name) {
        this.course = course;
        this.name = name;
        this.uploads = false;
    }

    public Type(Course course, String name, boolean uploads) {
        this.course = course;
        this.name = name;
        this.uploads = uploads;
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

    public boolean isUploads() {
        return uploads;
    }

    public void setUploads(boolean uploads) {
        this.uploads = uploads;
    }
}
