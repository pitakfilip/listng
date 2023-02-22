package sk.fmfi.listng.domain.taskset;

import sk.fmfi.listng.domain.administration.Language;
import sk.fmfi.listng.domain.task.TaskConfiguration;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TaskSet {

    private Long id;

    private Long courseId;

    private CourseTaskSetType type;

    private String name;

    private String description;

    private Timestamp published;

    private Timestamp deadline;

    private int totalPoints;

    private boolean finalSum;

    private List<TaskConfiguration> tasks;

    private List<Language> languages;

    public TaskSet(Long course, CourseTaskSetType type, String name, String description, Timestamp published, Timestamp deadline, int totalPoints, boolean finalSum) {
        this.courseId = course;
        this.type = type;
        this.name = name;
        this.description = description;
        this.published = published;
        this.deadline = deadline;
        this.totalPoints = totalPoints;
        this.finalSum = finalSum;
        this.tasks = new ArrayList<>();
        this.languages = new ArrayList<>();
    }

    public Long getCourse() {
        return courseId;
    }

    public void setCourse(Long course) {
        this.courseId = course;
    }

    public CourseTaskSetType getType() {
        return type;
    }

    public void setType(CourseTaskSetType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getPublished() {
        return published;
    }

    public void setPublished(Timestamp published) {
        this.published = published;
    }

    public Timestamp getDeadline() {
        return deadline;
    }

    public void setDeadline(Timestamp deadline) {
        this.deadline = deadline;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public boolean isFinalSum() {
        return finalSum;
    }

    public void setFinalSum(boolean finalSum) {
        this.finalSum = finalSum;
    }

    public List<TaskConfiguration> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskConfiguration> tasks) {
        this.tasks = tasks;
    }

    public void addTasks(List<TaskConfiguration> tasks) {
        this.tasks.addAll(tasks);
    }

    public void removeTasks(List<TaskConfiguration> tasks) {
        this.tasks.removeAll(tasks);
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public void addLanguages(List<Language> languages) {
        this.languages.addAll(languages);
    }

    public void removeLanguages(List<Language> languages) {
        this.languages.removeAll(languages);
    }
}
