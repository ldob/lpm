package eu.ldob.lpm.be.repository;

import eu.ldob.lpm.be.model.ProjectStatusModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectStatusRepository extends JpaRepository<ProjectStatusModel, Long> {
}
