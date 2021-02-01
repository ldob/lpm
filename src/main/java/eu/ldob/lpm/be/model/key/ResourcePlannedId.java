package eu.ldob.lpm.be.model.key;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ResourcePlannedId implements Serializable {

    @Column(name = "project_id")
    private Long projectId;

    @Column(name = "week_id")
    private Integer weekId;

    private ResourcePlannedId() { }

    public ResourcePlannedId(Long projectId, Integer weekId) {
        this.projectId = projectId;
        this.weekId = weekId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        ResourcePlannedId that = (ResourcePlannedId) o;
        return Objects.equals(projectId, that.projectId) &&
                Objects.equals(weekId, that.weekId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, weekId);
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Integer getWeekId() {
        return weekId;
    }

    public void setWeekId(Long userId) {
        this.weekId = weekId;
    }
}
