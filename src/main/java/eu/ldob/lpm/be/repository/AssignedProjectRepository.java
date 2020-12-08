package eu.ldob.lpm.be.repository;

import eu.ldob.lpm.be.model.AssignedProjectModel;
import eu.ldob.lpm.be.model.UserModel;
import eu.ldob.lpm.be.model.key.AssignedProjectId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignedProjectRepository extends JpaRepository<AssignedProjectModel, AssignedProjectId> {

    List<AssignedProjectModel> findByUser(UserModel user);
}
