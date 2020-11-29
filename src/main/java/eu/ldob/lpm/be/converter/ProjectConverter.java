package eu.ldob.lpm.be.converter;

import eu.ldob.lpm.be.response.ProjectResponse;
import eu.ldob.lpm.be.model.ProjectModel;
import eu.ldob.lpm.be.request.ProjectRequest;
import org.springframework.stereotype.Component;

@Component
public class ProjectConverter implements ModelConverter<ProjectRequest, ProjectModel, ProjectResponse> {
    @Override
    public ProjectModel requestToModel(ProjectRequest request) {
        ProjectModel model = new ProjectModel();

        model.setName(request.getName());
        model.setDescription(request.getDescription());
        model.setStartDate(request.getStartDate());
        model.setPlannedEndDate(request.getPlannedEndDate());
        model.setEndDate(request.getEndDate());

        return model;
    }
    @Override
    public ProjectResponse modelToResponse(ProjectModel model) {
        ProjectResponse response = new ProjectResponse();

        response.setId(model.getId());
        response.setName(model.getName());
        response.setDescription(model.getDescription());
        response.setStartDate(model.getStartDate());
        response.setPlannedEndDate(model.getPlannedEndDate());
        response.setEndDate(model.getEndDate());

        return response;
    }
}
