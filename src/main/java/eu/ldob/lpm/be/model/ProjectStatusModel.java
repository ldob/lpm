package eu.ldob.lpm.be.model;

import eu.ldob.lpm.be.model.type.EProjectStatus;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="project_status")
public class ProjectStatusModel extends AModel<Long> {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne
    @JoinColumn(name="project_id", nullable=false)
    private ProjectModel project;

    @Column
    private Date date;

    @Column
    private EProjectStatus status;

    @Column(columnDefinition="LONGTEXT")
    private String tweet;

    @Column(columnDefinition="LONGTEXT")
    private String nextSteps;

    @Column(columnDefinition="LONGTEXT")
    private String problems;

    public ProjectStatusModel() {
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProjectModel getProject() {
        return project;
    }

    public void setProject(ProjectModel project) {
        this.project = project;
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
