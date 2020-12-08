package eu.ldob.lpm.be.response;

import eu.ldob.lpm.be.model.type.ETodoStatus;

import java.util.Date;

public class TodoResponse {

    private Long id;
    private MemberResponse assignedMember;
    private Date dueDate;
    private ETodoStatus status;
    private String description;

    public TodoResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
