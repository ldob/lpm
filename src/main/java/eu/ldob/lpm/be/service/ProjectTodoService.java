package eu.ldob.lpm.be.service;

import eu.ldob.lpm.be.converter.ProjectTodoConverter;
import eu.ldob.lpm.be.exception.LpmNoResultException;
import eu.ldob.lpm.be.model.ProjectModel;
import eu.ldob.lpm.be.model.ProjectTodoModel;
import eu.ldob.lpm.be.model.UserModel;
import eu.ldob.lpm.be.repository.ProjectTodoRepository;
import eu.ldob.lpm.be.request.ProjectTodoRequest;
import eu.ldob.lpm.be.response.ProjectTodoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProjectTodoService {

    @Autowired
    ProjectTodoConverter converter;
    @Autowired
    ProjectTodoRepository projectTodoRepository;
    @Autowired
    ServiceUtil util;

    public ProjectTodoResponse save(Long projectId, ProjectTodoRequest request, UserModel user) {
        if(projectId == null) {
            Optional<ProjectTodoModel> todo = projectTodoRepository.findById(request.getId());
            if(todo.isEmpty()) {
                throw new LpmNoResultException("Todo " + request.getId() + " not found");
            }

            projectId = todo.get().getProject().getId();
        }

        ProjectModel allowedProject = util.getAllowedProject(projectId, user);
        if(allowedProject != null) {
            ProjectTodoModel model = converter.requestToModel(request);
            model.setProject(allowedProject);

            return converter.modelToResponse(projectTodoRepository.save(model));
        }

        throw new LpmNoResultException("Project " + projectId + " not found");
    }

    public List<ProjectTodoResponse> findAll(UserModel user) {
        List<ProjectTodoResponse> todoList = new ArrayList<>();

        for(ProjectModel p : util.getAllowedProjects(user)) {
            for(ProjectTodoModel todo : projectTodoRepository.findAllByProject(p)) {
                todoList.add(converter.modelToResponse(todo));
            }
        }

        return todoList;
    }

    public List<ProjectTodoResponse> findByUser(UserModel user) throws LpmNoResultException {
        return converter.modelToResponseList(projectTodoRepository.findAllByAssignedUser(user));
    }

    public List<ProjectTodoResponse> findByProject(Long projectId, UserModel user) throws LpmNoResultException {
        ProjectModel allowedProject = util.getAllowedProject(projectId, user);

        if(allowedProject != null) {
            return converter.modelToResponseList(projectTodoRepository.findAllByProject(allowedProject));
        }

        throw new LpmNoResultException("Project " + projectId + " not found");
    }

    public ProjectTodoResponse findById(Long id, UserModel user) throws LpmNoResultException {
        Optional<ProjectTodoModel> model = projectTodoRepository.findById(id);

        if(model.isPresent()) {
            if(util.getAllowedProjects(user).contains(model.get().getProject())) {
                return converter.modelToResponse(model.get());
            }

            throw new LpmNoResultException("Todo " + id + " not allowed");
        }

        throw new LpmNoResultException("Todo " + id + " not found");
    }
}
