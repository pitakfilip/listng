package sk.fmfi.listng.domain.solution;

import sk.fmfi.listng.domain.administration.Attachement;
import sk.fmfi.listng.domain.test.Result;

import java.sql.Timestamp;

public class Version {

    private Long id;

    private Timestamp submitted;

    private String comment;

    private int points = 0;

    private Attachement attachement;

    private Result result;

    private int order = -1;

    public Version(Timestamp submitted, String comment, Attachement attachement) {
        this.submitted = submitted;
        this.comment = comment;
        this.attachement = attachement;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Timestamp submitted) {
        this.submitted = submitted;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Attachement getAttachement() {
        return attachement;
    }

    public void setAttachement(Attachement attachement) {
        this.attachement = attachement;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
