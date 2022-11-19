package sk.fmfi.listng.domain.solution;

import sk.fmfi.listng.domain.taskset.TaskSet;
import sk.fmfi.listng.domain.user.User;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    private TaskSet taskSet;

    private User owner;

    private User evaluator;

    private String evalComment;

    private int points = 0;

    private boolean modified = false;

    private boolean example = false;

    private List<Version> versions;

    public Solution(TaskSet taskSet, User owner) {
        this.taskSet = taskSet;
        this.owner = owner;
        this.versions = new ArrayList<>();
    }

    public TaskSet getTaskSet() {
        return taskSet;
    }

    public void setTaskSet(TaskSet taskSet) {
        this.taskSet = taskSet;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getEvaluator() {
        return evaluator;
    }

    public void setEvaluator(User evaluator) {
        this.evaluator = evaluator;
    }

    public String getEvalComment() {
        return evalComment;
    }

    public void setEvalComment(String evalComment) {
        this.evalComment = evalComment;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean isModified() {
        return modified;
    }

    public void setModified(boolean modified) {
        this.modified = modified;
    }

    public boolean isExample() {
        return example;
    }

    public void setExample(boolean example) {
        this.example = example;
    }

    public List<Version> getVersions() {
        return versions;
    }

    public void setVersions(List<Version> versions) {
        this.versions = versions;
    }

    public void addVersion(Version version) {
        this.versions.add(version);
    }
}
