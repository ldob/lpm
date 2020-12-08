package eu.ldob.lpm.be.model;

import eu.ldob.lpm.be.model.type.ETodoStatus;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="project_todo")
public class ProjectTodoModel extends AModel<Long> {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne
    @JoinColumn(name="project_id", nullable=false)
    private ProjectModel project;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private UserModel assignedUser;

    @Column
    private Date dueDate;

    @Column
    private ETodoStatus status;

    @Column
    private String description;

    public ProjectTodoModel() {
    }

    @Override
    public Long getId() {
        return id;
    }

    public ProjectModel getProject() {
        return project;
    }

    public void setProject(ProjectModel project) {
        this.project = project;
    }

    public UserModel getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(UserModel assignedUser) {
        this.assignedUser = assignedUser;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public ETodoStatus getStatus() {
        return status;
    }

    public void setStatus(ETodoStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
