package eu.ldob.lpm.be.repository;

import eu.ldob.lpm.be.model.ProjectModel;
import eu.ldob.lpm.be.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectModel, Long> {

    List<ProjectModel> findAllByAssignedUsers(UserModel assignedUser);
}
