package eu.ldob.lpm.be.service;

import eu.ldob.lpm.be.converter.ProjectConverter;
import eu.ldob.lpm.be.response.ProjectResponse;
import eu.ldob.lpm.be.exception.LpmNoResultException;
import eu.ldob.lpm.be.model.ProjectModel;
import eu.ldob.lpm.be.repository.ProjectRepository;
import eu.ldob.lpm.be.request.ProjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProjectService {

    @Autowired
    ProjectConverter converter;
    @Autowired
    ProjectRepository repository;

    public ProjectResponse add(final ProjectRequest request) {
        ProjectModel model = converter.requestToModel(request);

        model.setStartDate(new Date());

        return converter.modelToResponse(repository.save(model));
    }

    public List<ProjectResponse> findAll() {
        List<ProjectModel> modelList = repository.findAll();
        List<ProjectResponse> responseList = new ArrayList<>();

        for (ProjectModel model : modelList) {
            responseList.add(converter.modelToResponse(model));
        }

        return responseList;
    }

    public ProjectResponse findById(Long id) throws LpmNoResultException {
        Optional<ProjectModel> model = repository.findById(id);

        if(model.isPresent()) {
            return converter.modelToResponse(model.get());
        }

        throw new LpmNoResultException("Project not found");
    }
}
