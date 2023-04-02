package sk.fmfi.listng.domain.solution;

import sk.fmfi.listng.domain.taskset.TaskSet;
import sk.fmfi.listng.domain.user.User;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    // TODO pridanie version -> priradenie jej order value
    // TODO getovanie najlepsej verzie (jej vysledok bude zobrazovany na FE a zaratany do suctu bodov)

    private Long id;

    private Long taskSetId;

    private User owner;

    private User evaluator;

    private String evalComment;

    private int points = 0;

    private boolean modified = false;

    private boolean example = false;

    private List<Version> versions;

    public Solution(Long taskSetId, User owner) {
        this.taskSetId = taskSetId;
        this.owner = owner;
        this.versions = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTaskSetId() {
        return taskSetId;
    }

    public void setTaskSetId(Long taskSetId) {
        this.taskSetId = taskSetId;
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
