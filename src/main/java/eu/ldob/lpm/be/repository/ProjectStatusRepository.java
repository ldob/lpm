package eu.ldob.lpm.be.repository;

import eu.ldob.lpm.be.model.ProjectModel;
import eu.ldob.lpm.be.model.ProjectStatusModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectStatusRepository extends JpaRepository<ProjectStatusModel, Long> {

    Optional<ProjectStatusModel> findTopByProjectOrderByDateDesc(ProjectModel project);

}
