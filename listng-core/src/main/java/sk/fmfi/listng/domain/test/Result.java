package sk.fmfi.listng.domain.test;

public class Result {

    private Long executionTime; //ms

    private boolean success;

    private int points;

    private String output;

    public Result(Long executionTime, boolean success, int points, String output) {
        this.executionTime = executionTime;
        this.success = success;
        this.points = points;
        this.output = output;
    }

    public Long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Long executionTime) {
        this.executionTime = executionTime;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
}
