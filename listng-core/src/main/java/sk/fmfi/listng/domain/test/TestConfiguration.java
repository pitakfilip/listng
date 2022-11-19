package sk.fmfi.listng.domain.test;

import sk.fmfi.listng.domain.task.TaskConfiguration;

public class TestConfiguration {

    private Test test;

    private TaskConfiguration task;

    private int weight = 100; // %

    private long timeout = 15000; // ms

    public TestConfiguration(Test test, TaskConfiguration task) {
        this.test = test;
        this.task = task;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public TaskConfiguration getTask() {
        return task;
    }

    public void setTask(TaskConfiguration task) {
        this.task = task;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }
}
