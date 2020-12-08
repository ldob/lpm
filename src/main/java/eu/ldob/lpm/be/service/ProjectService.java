package eu.ldob.lpm.be.service;

import eu.ldob.lpm.be.converter.ProjectConverter;
import eu.ldob.lpm.be.exception.LpmNoResultException;
import eu.ldob.lpm.be.model.AssignedProjectModel;
import eu.ldob.lpm.be.model.ProjectModel;
import eu.ldob.lpm.be.model.UserModel;
import eu.ldob.lpm.be.model.type.EProjectRole;
import eu.ldob.lpm.be.repository.AssignedProjectRepository;
import eu.ldob.lpm.be.repository.ProjectRepository;
import eu.ldob.lpm.be.repository.UserRepository;
import eu.ldob.lpm.be.request.ProjectRequest;
import eu.ldob.lpm.be.response.ProjectListResponse;
import eu.ldob.lpm.be.response.ProjectResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProjectService {

    @Autowired
    ProjectConverter converter;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    AssignedProjectRepository assignedProjectRepository;
    @Autowired
    UserRepository userRepository;

    public ProjectResponse add(final ProjectRequest request) {
        ProjectModel model = converter.requestToModel(request);

        return converter.modelToResponse(projectRepository.save(model));
    }

    public ProjectListResponse findAll(UserModel user) {
        List<AssignedProjectModel> assignedProjects = assignedProjectRepository.findByUser(user);

        ProjectListResponse responseList = new ProjectListResponse();
        for (AssignedProjectModel model : assignedProjects) {
            responseList.addProject(model.getRole(), converter.modelToResponse(model.getProject()));
        }

        return responseList;
    }

    public ProjectResponse findById(Long id) throws LpmNoResultException {
        Optional<ProjectModel> model = projectRepository.findById(id);
        if(model.isPresent()) {
            return converter.modelToResponse(model.get());
        }

        throw new LpmNoResultException("Project " + id + " not found");
    }

    public void addMember(Long projectId, Long memberId, EProjectRole role) {
        Optional<ProjectModel> project = projectRepository.findById(projectId);
        if(project.isEmpty()) {
            throw new LpmNoResultException("Project not found");
        }

        Optional<UserModel> user = userRepository.findById(memberId);
        if(user.isEmpty()) {
            throw new LpmNoResultException("Member not found");
        }

        project.get().addAssignedUser(new AssignedProjectModel(project.get(), user.get(), role));
    }

    public void removeMember(Long projectId, Long memberId, EProjectRole role) {
        Optional<ProjectModel> project = projectRepository.findById(projectId);
        if(project.isEmpty()) {
            throw new LpmNoResultException("Project not found");
        }

        Optional<UserModel> user = userRepository.findById(memberId);
        if(user.isEmpty()) {
            throw new LpmNoResultException("Member not found");
        }

        project.get().removeAssignedUser(new AssignedProjectModel(project.get(), user.get(), role));
    }
}
