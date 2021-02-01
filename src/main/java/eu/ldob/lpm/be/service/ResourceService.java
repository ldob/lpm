package eu.ldob.lpm.be.service;

import eu.ldob.lpm.be.converter.ProjectConverter;
import eu.ldob.lpm.be.converter.WeekConverter;
import eu.ldob.lpm.be.model.ProjectModel;
import eu.ldob.lpm.be.model.ResourcePlannedModel;
import eu.ldob.lpm.be.model.UserModel;
import eu.ldob.lpm.be.model.WeekModel;
import eu.ldob.lpm.be.model.type.EProjectRole;
import eu.ldob.lpm.be.repository.ResourcePlannedRepository;
import eu.ldob.lpm.be.repository.WeekRepository;
import eu.ldob.lpm.be.response.ResourcePlannedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ResourceService {

    @Autowired
    ProjectConverter projectConverter;
    @Autowired
    WeekConverter weekConverter;
    @Autowired
    ResourcePlannedRepository resourcePlannedRepository;
    @Autowired
    WeekRepository weekRepository;
    @Autowired
    ServiceUtil util;

    public ResourcePlannedResponse findAllPlanned(Integer year, UserModel user) {
        List<ProjectModel> projects = util.getAllowedProjects(user, EProjectRole.MANAGE);
        List<WeekModel> weeks = weekRepository.findAllByYear(year);

        ResourcePlannedResponse response = new ResourcePlannedResponse();

        response.setProjects(projectConverter.modelToResponseList(projects));
        response.setWeeks(weekConverter.modelToResponseList(weeks));

        for(ProjectModel project : projects) {
            for(WeekModel week : weeks) {
                Optional<ResourcePlannedModel> resource = resourcePlannedRepository.findByProjectAndWeek(project, week);
                response.addValues(project.getId(), week.getId(), resource.isPresent() ? resource.get().getResource() : 0);
            }
        }

        return response;
    }
}
