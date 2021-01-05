package eu.ldob.lpm.be.repository;

import eu.ldob.lpm.be.model.ProjectModel;
import eu.ldob.lpm.be.model.ProjectTodoModel;
import eu.ldob.lpm.be.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectTodoRepository extends JpaRepository<ProjectTodoModel, Long> {

    List<ProjectTodoModel> findAllByProject(ProjectModel project);

    List<ProjectTodoModel> findAllByAssignedUser(UserModel assignedUser);
}
