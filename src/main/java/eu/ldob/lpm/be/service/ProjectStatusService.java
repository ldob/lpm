package eu.ldob.lpm.be.service;

import eu.ldob.lpm.be.converter.ProjectStatusConverter;
import eu.ldob.lpm.be.exception.LpmNoResultException;
import eu.ldob.lpm.be.model.ProjectModel;
import eu.ldob.lpm.be.model.ProjectStatusModel;
import eu.ldob.lpm.be.model.UserModel;
import eu.ldob.lpm.be.repository.ProjectStatusRepository;
import eu.ldob.lpm.be.request.ProjectStatusRequest;
import eu.ldob.lpm.be.response.ProjectStatusResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProjectStatusService {

    @Autowired
    ProjectStatusConverter converter;
    @Autowired
    ProjectStatusRepository projectStatusRepository;
    @Autowired
    ServiceUtil util;

    public ProjectStatusResponse save(Long projectId, ProjectStatusRequest request, UserModel user) {

        ProjectModel allowedProject = util.getAllowedProject(projectId, user);
        if(allowedProject != null) {
            ProjectStatusModel model = converter.requestToModel(request);
            model.setProject(allowedProject);

            return converter.modelToResponse(projectStatusRepository.save(model));
        }

        throw new LpmNoResultException("Project " + projectId + " not found");
    }

    public List<ProjectStatusResponse> findAll(Long projectId, UserModel user) {

        ProjectModel allowedProject = util.getAllowedProject(projectId, user);
        if(allowedProject != null) {
            List<ProjectStatusResponse> statusList = new ArrayList<>();
            for(ProjectStatusModel status : projectStatusRepository.findAllByProjectOrderByDateDesc(allowedProject)) {
                statusList.add(converter.modelToResponse(status));
            }
            return statusList;
        }

        throw new LpmNoResultException("Project " + projectId + " not found");
    }

    public ProjectStatusResponse findLatest(Long projectId, UserModel user) {

        ProjectModel allowedProject = util.getAllowedProject(projectId, user);
        if(allowedProject != null) {
            Optional<ProjectStatusModel> model = projectStatusRepository.findTopByProjectOrderByDateDesc(allowedProject);
            if(model.isPresent()) {
                return converter.modelToResponse(model.get());
            }

            return null;
        }

        throw new LpmNoResultException("Project " + projectId + " not found");
    }
}
