package eu.ldob.lpm.be.response;

import eu.ldob.lpm.be.model.type.EProjectStatus;

import java.util.Date;

public class ProjectStatusResponse {

    private Long id;
    private Date date;
    private EProjectStatus status;
    private String tweet;
    private String nextSteps;
    private String problems;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public EProjectStatus getStatus() {
        return status;
    }

    public void setStatus(EProjectStatus status) {
        this.status = status;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public String getNextSteps() {
        return nextSteps;
    }

    public void setNextSteps(String nextSteps) {
        this.nextSteps = nextSteps;
    }

    public String getProblems() {
        return problems;
    }

    public void setProblems(String problems) {
        this.problems = problems;
    }
}
