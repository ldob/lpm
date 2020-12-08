package eu.ldob.lpm.be.model;

import eu.ldob.lpm.be.model.type.EProjectStatus;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="project_status")
public class ProjectStatusModel extends AModel<Long> {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne
    @JoinColumn(name="project_id", nullable=false)
    private ProjectModel project;

    @Column
    private Date date;

    @Column
    private EProjectStatus status;

    @Column
    private String tweet;

    @Column
    private String nextSteps;

    @Column
    private String problems;

    public ProjectStatusModel() {
    }

    @Override
    public Long getId() {
        return id;
    }
}
