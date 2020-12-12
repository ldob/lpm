package eu.ldob.lpm.be.response;

import eu.ldob.lpm.be.model.type.EPriority;
import eu.ldob.lpm.be.model.type.EProjectRole;
import eu.ldob.lpm.be.model.type.EProjectStatus;

import java.util.*;

public class ProjectResponse {

    private Long id;
    private String name;
    private String description;
    private EPriority priority;
    private Date startDate;
    private Date plannedEndDate;
    private Date endDate;
    private Float resourceBudget;
    private EProjectStatus status;
    private Map<EProjectRole, List<MemberResponse>> assignedMembers = new HashMap<>();
    private List<TodoResponse> todos = new ArrayList<>();

    public ProjectResponse() {
    }

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

    public Map<EProjectRole, List<MemberResponse>> getAssignedMembers() {
        return assignedMembers;
    }

    public void addAssignedMember(EProjectRole role, MemberResponse member) {
        if(assignedMembers.containsKey(role)) {
            assignedMembers.get(role).add(member);
        }
        else {
            List<MemberResponse> list = new ArrayList<>();
            list.add(member);
            assignedMembers.put(role, list);
        }
    }

    public List<TodoResponse> getTodos() {
        return todos;
    }

    public void addTodo(TodoResponse todo) {
        todos.add(todo);
    }

    public EProjectStatus getStatus() {
        return status;
    }

    public void setStatus(EProjectStatus status) {
        this.status = status;
    }
}
