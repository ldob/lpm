package eu.ldob.lpm.be.model;

import eu.ldob.lpm.be.model.type.EPriority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="project")
public class ProjectModel extends AModel<Long> {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private EPriority priority;

    @Column
    private Date startDate;

    @Column
    private Date plannedEndDate;

    @Column
    private Date endDate;

    @Column
    private Float resourceBudget;

    @OneToMany(
            mappedBy = "project",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ProjectStatusModel> status = new ArrayList<>();

    @OneToMany(
        mappedBy = "project",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<AssignedProjectModel> assignedUsers = new ArrayList<>();

    @OneToMany(
        mappedBy = "project",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<ProjectTodoModel> todos = new ArrayList<>();

    public ProjectModel() {
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public EPriority getPriority() {
        return priority;
    }

    public void setPriority(EPriority priority) {
        this.priority = priority;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getPlannedEndDate() {
        return plannedEndDate;
    }

    public void setPlannedEndDate(Date plannedEndDate) {
        this.plannedEndDate = plannedEndDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Float getResourceBudget() {
        return resourceBudget;
    }

    public void setResourceBudget(Float resourceBudget) {
        this.resourceBudget = resourceBudget;
    }

    public List<ProjectStatusModel> getStatus() {
        return status;
    }

    public void setStatus(List<ProjectStatusModel> status) {
        this.status = status;
    }

    public void addStatus(ProjectStatusModel status) {
        this.status.add(status);
    }

    public void removeStatus(ProjectStatusModel status) {
        this.status.remove(status);
    }

    public List<AssignedProjectModel> getAssignedUsers() {
        return assignedUsers;
    }

    public void setAssignedUsers(List<AssignedProjectModel> assignedUsers) {
        this.assignedUsers = assignedUsers;
    }

    public void addAssignedUser(AssignedProjectModel assignedProjectModel) {
        this.assignedUsers.add(assignedProjectModel);
    }

    public void removeAssignedUser(AssignedProjectModel assignedProjectModel) {
        this.assignedUsers.remove(assignedProjectModel);
    }

    public List<ProjectTodoModel> getTodos() {
        return todos;
    }

    public void setTodos(List<ProjectTodoModel> todos) {
        this.todos = todos;
    }
}
