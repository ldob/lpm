package eu.ldob.lpm.be.repository;

import eu.ldob.lpm.be.model.ProjectModel;
import eu.ldob.lpm.be.model.ResourcePlannedModel;
import eu.ldob.lpm.be.model.WeekModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResourcePlannedRepository extends JpaRepository<ResourcePlannedModel, Long> {

    Optional<ResourcePlannedModel> findByProjectAndWeek(ProjectModel project, WeekModel week);
}
