package eu.ldob.lpm.be.response;

import eu.ldob.lpm.be.model.type.ETodoStatus;

import java.util.Date;

public class ProjectTodoResponse {

    private Long id;
    private Long projectId;
    private String projectName;
    private MemberResponse assignedMember;
    private Date dueDate;
    private ETodoStatus status;
    private String description;

    public ProjectTodoResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public MemberResponse getAssignedMember() {
        return assignedMember;
    }

    public void setAssignedMember(MemberResponse assignedMember) {
        this.assignedMember = assignedMember;
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
