package eu.ldob.lpm.be.repository;

import eu.ldob.lpm.be.model.type.ERole;
import eu.ldob.lpm.be.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleModel, Long> {
    Optional<RoleModel> findByName(ERole name);
}
