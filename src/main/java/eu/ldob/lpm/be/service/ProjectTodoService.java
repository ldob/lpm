package eu.ldob.lpm.be.service;

import eu.ldob.lpm.be.converter.ProjectConverter;
import eu.ldob.lpm.be.exception.LpmNoResultException;
import eu.ldob.lpm.be.model.UserModel;
import eu.ldob.lpm.be.repository.ProjectTodoRepository;
import eu.ldob.lpm.be.repository.UserRepository;
import eu.ldob.lpm.be.request.ProjectTodoRequest;
import eu.ldob.lpm.be.response.ProjectResponse;
import eu.ldob.lpm.be.response.ProjectTodoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProjectTodoService {

    @Autowired
    ProjectConverter converter;
    @Autowired
    ProjectTodoRepository projectTodoRepository;
    @Autowired
    UserRepository userRepository;

    public ProjectTodoResponse save(Long projectId, ProjectTodoRequest request, UserModel user) {
        return null;
    }

    public List<ProjectTodoResponse> findAll(UserModel user) {
        return null;
    }

    public List<ProjectTodoResponse> findByUser(UserModel user) throws LpmNoResultException {
        return null;
    }

    public ProjectResponse findById(Long id, UserModel user) throws LpmNoResultException {
        throw new LpmNoResultException("Todo " + id + " not found");
    }

    public ProjectResponse findByIdInternal(Long id) throws LpmNoResultException {
        throw new LpmNoResultException("Project " + id + " not found");
    }
}
