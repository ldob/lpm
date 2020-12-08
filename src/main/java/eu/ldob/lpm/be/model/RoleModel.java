package eu.ldob.lpm.be.model;

import eu.ldob.lpm.be.model.type.ERole;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class RoleModel extends AModel<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

    public RoleModel() { }

    public RoleModel(ERole name) {
        this.name = name;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public ERole getName() {
        return name;
    }

    public void setName(ERole name) {
        this.name = name;
    }
}
