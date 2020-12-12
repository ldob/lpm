package eu.ldob.lpm.be.service;

import eu.ldob.lpm.be.converter.ProjectStatusConverter;
import eu.ldob.lpm.be.exception.LpmNoResultException;
import eu.ldob.lpm.be.model.AssignedProjectModel;
import eu.ldob.lpm.be.model.ProjectModel;
import eu.ldob.lpm.be.model.ProjectStatusModel;
import eu.ldob.lpm.be.model.UserModel;
import eu.ldob.lpm.be.model.type.ERole;
import eu.ldob.lpm.be.repository.AssignedProjectRepository;
import eu.ldob.lpm.be.repository.ProjectRepository;
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
    ProjectRepository projectRepository;
    @Autowired
    ProjectStatusRepository projectStatusRepository;
    @Autowired
    AssignedProjectRepository assignedProjectRepository;

    public ProjectStatusResponse save(Long projectId, ProjectStatusRequest request, UserModel user) {

        ProjectModel allowedProject = getAllowedProject(projectId, user);
        if(allowedProject != null) {
            ProjectStatusModel model = converter.requestToModel(request);
            model.setProject(allowedProject);

            return converter.modelToResponse(projectStatusRepository.save(model));
        }

        throw new LpmNoResultException("Project " + projectId + " not found");
    }

    public List<ProjectStatusResponse> findAll(Long projectId, UserModel user) {

        ProjectModel allowedProject = getAllowedProject(projectId, user);
        if(allowedProject != null) {
            List<ProjectStatusResponse> statusList = new ArrayList<>();
            for(ProjectStatusModel status : allowedProject.getStatus()) {
                statusList.add(converter.modelToResponse(status));
            }
            return statusList;
        }

        throw new LpmNoResultException("Project " + projectId + " not found");
    }

    public ProjectStatusResponse findLatest(Long projectId, UserModel user) {

        ProjectModel allowedProject = getAllowedProject(projectId, user);
        if(allowedProject != null) {
            Optional<ProjectStatusModel> model = projectStatusRepository.findTopByProjectOrderByDateDesc(allowedProject);
            if(model.isPresent()) {
                return converter.modelToResponse(model.get());
            }

            return null;
        }

        throw new LpmNoResultException("Project " + projectId + " not found");
    }

    private ProjectModel getAllowedProject(Long projectId, UserModel user) {
        Optional<ProjectModel> globalProjectModel = projectRepository.findById(projectId);
        ProjectModel allowedProject = null;
        if(globalProjectModel.isPresent()) {

            if(user.getRoles().contains(ERole.ROLE_ADMIN)) {
                allowedProject = globalProjectModel.get();
            }

            Optional<AssignedProjectModel> assignedProjectModel = assignedProjectRepository.findByUserAndProject(user, globalProjectModel.get());
            if(assignedProjectModel.isPresent()) {
                allowedProject = assignedProjectModel.get().getProject();
            }
        }

        return allowedProject;
    }
}
