package eu.ldob.lpm.be.converter;

import eu.ldob.lpm.be.model.ProjectStatusModel;
import eu.ldob.lpm.be.request.ProjectStatusRequest;
import eu.ldob.lpm.be.response.ProjectStatusResponse;
import org.springframework.stereotype.Component;

@Component
public class ProjectStatusConverter implements ModelConverter<ProjectStatusRequest, ProjectStatusModel, ProjectStatusResponse> {

    @Override
    public ProjectStatusModel requestToModel(ProjectStatusRequest request) {
        ProjectStatusModel model = new ProjectStatusModel();

        model.setId(request.getId());
        model.setDate(request.getDate());
        model.setStatus(request.getStatus());
        model.setTweet(request.getTweet());
        model.setNextSteps(request.getNextSteps());
        model.setProblems(request.getProblems());

        return model;
    }

    @Override
    public ProjectStatusResponse modelToResponse(ProjectStatusModel model) {
        ProjectStatusResponse response = new ProjectStatusResponse();

        response.setId(model.getId());
        response.setDate(model.getDate());
        response.setStatus(model.getStatus());
        response.setTweet(model.getTweet());
        response.setNextSteps(model.getNextSteps());
        response.setProblems(model.getProblems());

        return response;
    }
}
