package eu.ldob.lpm.be.repository;

import eu.ldob.lpm.be.model.ProjectTodoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectTodoRepository extends JpaRepository<ProjectTodoModel, Long> {
}
