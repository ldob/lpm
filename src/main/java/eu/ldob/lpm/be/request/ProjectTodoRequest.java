package eu.ldob.lpm.be.request;

import eu.ldob.lpm.be.model.type.ETodoStatus;

import java.util.Date;

public class ProjectTodoRequest {

    private Long id;
    private MemberRequest assignedMember;
    private Date dueDate;
    private ETodoStatus status;
    private String description;

    public ProjectTodoRequest() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MemberRequest getAssignedMember() {
        return assignedMember;
    }

    public void setAssignedMember(MemberRequest assignedMember) {
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
