package sk.fmfi.listng.domain.task;

import sk.fmfi.listng.domain.test.TestConfiguration;

import java.util.ArrayList;
import java.util.List;

public class TaskConfiguration {

    private Task task;

    private int minPoints;

    private int maxPoints;

    private List<TestConfiguration> tests;

    public TaskConfiguration(Task task, int minPoints, int maxPoints) {
        this.task = task;
        this.minPoints = minPoints;
        this.maxPoints = maxPoints;
        this.tests = new ArrayList<>();
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public int getMinPoints() {
        return minPoints;
    }

    public void setMinPoints(int minPoints) {
        this.minPoints = minPoints;
    }

    public int getMaxPoints() {
        return maxPoints;
    }

    public void setMaxPoints(int maxPoints) {
        this.maxPoints = maxPoints;
    }

    public List<TestConfiguration> getTests() {
        return tests;
    }

    public void setTests(List<TestConfiguration> tests) {
        this.tests = tests;
    }

    public void addTests(List<TestConfiguration> tests) {
        this.tests.addAll(tests);
    }

    public void removeTests(List<TestConfiguration> tests) {
        this.tests.removeAll(tests);
    }
}
