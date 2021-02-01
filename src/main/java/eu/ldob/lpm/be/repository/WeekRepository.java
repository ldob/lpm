package eu.ldob.lpm.be.repository;

import eu.ldob.lpm.be.model.WeekModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeekRepository extends JpaRepository<WeekModel, Integer> {

    List<WeekModel> findAllByYear(Integer year);
}
