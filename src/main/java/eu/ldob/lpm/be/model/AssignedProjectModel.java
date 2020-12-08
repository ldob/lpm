package eu.ldob.lpm.be.model;

import eu.ldob.lpm.be.model.key.AssignedProjectId;
import eu.ldob.lpm.be.model.type.EProjectRole;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="assigned_project")
public class AssignedProjectModel {

    @EmbeddedId
    private AssignedProjectId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("projectId")
    private ProjectModel project;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private UserModel user;

    @Column(name = "role")
    @MapsId("role")
    private EProjectRole role;

    private AssignedProjectModel() { }

    public AssignedProjectModel(ProjectModel project, UserModel user, EProjectRole role) {
        this.id = new AssignedProjectId(project.getId(), user.getId());
        this.project = project;
        this.user = user;
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        AssignedProjectModel that = (AssignedProjectModel) o;
        return Objects.equals(this.project, that.project) && Objects.equals(this.user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(project, user);
    }

    public AssignedProjectId getId() {
        return id;
    }

    public ProjectModel getProject() {
        return project;
    }

    public void setProject(ProjectModel project) {
        this.project = project;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public EProjectRole getRole() {
        return role;
    }

    public void setRole(EProjectRole role) {
        this.role = role;
    }
}
