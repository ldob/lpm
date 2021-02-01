package eu.ldob.lpm.be.model;

import eu.ldob.lpm.be.model.key.ResourcePlannedId;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="resource_planned")
public class ResourcePlannedModel {

    @EmbeddedId
    private ResourcePlannedId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("projectId")
    private ProjectModel project;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("weekId")
    private WeekModel week;

    @Column
    private Float resource;

    private ResourcePlannedModel() { }

    public ResourcePlannedModel(ProjectModel project, WeekModel week, Float resource) {
        this.id = new ResourcePlannedId(project.getId(), week.getId());
        this.project = project;
        this.week = week;
        this.resource = resource;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        ResourcePlannedModel that = (ResourcePlannedModel) o;
        return Objects.equals(this.project, that.project) && Objects.equals(this.week, that.week);
    }

    @Override
    public int hashCode() {
        return Objects.hash(project, week);
    }

    public ResourcePlannedId getId() {
        return id;
    }

    public ProjectModel getProject() {
        return project;
    }

    public void setProject(ProjectModel project) {
        this.project = project;
    }

    public WeekModel getWeek() {
        return week;
    }

    public void setWeek(WeekModel week) {
        this.week = week;
    }

    public Float getResource() {
        return resource;
    }

    public void setResource(Float resource) {
        this.resource = resource;
    }
}
