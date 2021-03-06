package eu.ldob.lpm.be.request;

import eu.ldob.lpm.be.model.type.EPriority;

import java.util.Date;

public class ProjectRequest {

    private Long id;
    private String name;
    private String description;
    private EPriority priority;
    private Date startDate;
    private Date plannedEndDate;
    private Date endDate;
    private Float resourceBudget;

    public ProjectRequest() {
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
}
