package eu.ldob.lpm.be.service;

import eu.ldob.lpm.be.model.AssignedProjectModel;
import eu.ldob.lpm.be.model.ProjectModel;
import eu.ldob.lpm.be.model.UserModel;
import eu.ldob.lpm.be.model.type.EProjectRole;
import eu.ldob.lpm.be.model.type.ERole;
import eu.ldob.lpm.be.repository.AssignedProjectRepository;
import eu.ldob.lpm.be.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ServiceUtil {

    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    AssignedProjectRepository assignedProjectRepository;

    protected ProjectModel getAllowedProject(Long projectId, UserModel user) {
        Optional<ProjectModel> globalProjectModel = projectRepository.findById(projectId);
        ProjectModel allowedProject = null;
        if(globalProjectModel.isPresent()) {

            if(user.isAdmin()) {
                allowedProject = globalProjectModel.get();
            }

            Optional<AssignedProjectModel> assignedProjectModel = assignedProjectRepository.findByUserAndProject(user, globalProjectModel.get());
            if(assignedProjectModel.isPresent()) {
                allowedProject = assignedProjectModel.get().getProject();
            }
        }

        return allowedProject;
    }

    protected List<ProjectModel> getAllowedProjects(UserModel user) {
        if(user.getRoles().contains(ERole.ROLE_ADMIN)) {
            return projectRepository.findAll();
        }

        List<ProjectModel> projectList = new ArrayList<>();
        for (AssignedProjectModel model : assignedProjectRepository.findByUser(user)) {
            projectList.add(model.getProject());
        }

        return projectList;
    }

    protected List<ProjectModel> getAllowedProjects(UserModel user, EProjectRole role) {
        List<ProjectModel> projectList = new ArrayList<>();

        for (AssignedProjectModel model : assignedProjectRepository.findByUser(user)) {
            if(model.getRole().equals(role)) {
                projectList.add(model.getProject());
            }
        }

        return projectList;
    }

}
