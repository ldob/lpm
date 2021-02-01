package eu.ldob.lpm.be.service;

import eu.ldob.lpm.be.converter.MemberConverter;
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
import eu.ldob.lpm.be.response.MemberResponse;
import eu.ldob.lpm.be.response.ProjectListResponse;
import eu.ldob.lpm.be.response.ProjectResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProjectService {

    @Autowired
    ProjectConverter converter;
    @Autowired
    MemberConverter memberConverter;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    AssignedProjectRepository assignedProjectRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ServiceUtil util;

    public ProjectResponse save(ProjectRequest request, UserModel user) {
        ProjectModel model = converter.requestToModel(request);

        if(request.getId() != null) {
            Optional<ProjectModel> p = projectRepository.findById(request.getId());
            if (p.isPresent()) {
                model.setAssignedUsers(p.get().getAssignedUsers());
                model.setTodos(p.get().getTodos());
            }
        }

        model = projectRepository.save(model);

        if(model.getAssignedUsers().size() < 1) {
            model.addAssignedUser(new AssignedProjectModel(model, user, EProjectRole.MANAGE));
        }

        return converter.modelToResponse(model);
    }

    public ProjectListResponse findAll(UserModel user) {
        List<AssignedProjectModel> assignedProjects = assignedProjectRepository.findByUser(user);

        ProjectListResponse responseList = new ProjectListResponse();
        for (AssignedProjectModel model : assignedProjects) {
            responseList.addProject(model.getRole(), converter.modelToResponse(model.getProject()));
        }

        return responseList;
    }

    public ProjectResponse findById(Long id, UserModel user) throws LpmNoResultException {
        ProjectModel model = util.getAllowedProject(id, user);

        if(model != null) {
            return converter.modelToResponse(model);
        }

        throw new LpmNoResultException("Project " + id + " not found");
    }

    public ProjectResponse findByIdInternal(Long id) throws LpmNoResultException {
        Optional<ProjectModel> model = projectRepository.findById(id);
        if(model.isPresent()) {
            return converter.modelToResponse(model.get());
        }

        throw new LpmNoResultException("Project " + id + " not found");
    }

    public List<MemberResponse> findAllAssignedMembers(Long projectId, List<EProjectRole> roles, UserModel user) {
        ProjectModel model = util.getAllowedProject(projectId, user);

        if(model != null) {
            List<MemberResponse> assignedMembers = new ArrayList<>();
            for(AssignedProjectModel m : model.getAssignedUsers()) {
                if(roles.contains(m.getRole())) {
                    assignedMembers.add(memberConverter.modelToResponse(m.getUser()));
                }
            }

            return assignedMembers;
        }

        throw new LpmNoResultException("Project " + projectId + " not found");
    }

    public void addMember(Long projectId, String memberUsername, EProjectRole role, UserModel user) {
        ProjectModel project = util.getAllowedProject(projectId, user);

        if(project != null) {

            Optional<UserModel> member = userRepository.findByUsername(memberUsername);
            if(member.isEmpty()) {
                throw new LpmNoResultException("Member " + memberUsername + " not found");
            }

            project.addAssignedUser(new AssignedProjectModel(project, member.get(), role));
        }
    }

    public void removeMember(Long projectId, String memberUsername, EProjectRole role, UserModel user) {
        ProjectModel project = util.getAllowedProject(projectId, user);

        if(project != null) {

            Optional<UserModel> member = userRepository.findByUsername(memberUsername);
            if(member.isEmpty()) {
                throw new LpmNoResultException("Member " + memberUsername + " not found");
            }

            project.removeAssignedUser(new AssignedProjectModel(project, member.get(), role));
        }
    }
}
