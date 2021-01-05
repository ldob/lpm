package eu.ldob.lpm.be.converter;

import eu.ldob.lpm.be.model.ProjectTodoModel;
import eu.ldob.lpm.be.request.ProjectTodoRequest;
import eu.ldob.lpm.be.response.ProjectTodoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProjectTodoConverter extends ModelConverter<ProjectTodoRequest, ProjectTodoModel, ProjectTodoResponse> {

    @Autowired
    MemberConverter memberConverter;

    @Override
    public ProjectTodoModel requestToModel(ProjectTodoRequest request) {
        ProjectTodoModel model = new ProjectTodoModel();

        model.setId(request.getId());
        model.setDescription(request.getDescription());
        model.setStatus(request.getStatus());
        model.setDueDate(request.getDueDate());

        model.setAssignedUser(memberConverter.requestToModel(request.getAssignedMember()));

        return model;
    }

    @Override
    public ProjectTodoResponse modelToResponse(ProjectTodoModel model) {
        ProjectTodoResponse response = new ProjectTodoResponse();

        response.setId(model.getId());
        response.setProjectId(model.getProject().getId());
        response.setProjectName(model.getProject().getName());
        response.setDescription(model.getDescription());
        response.setStatus(model.getStatus());
        response.setDueDate(model.getDueDate());
        response.setAssignedMember(memberConverter.modelToResponse(model.getAssignedUser()));

        return response;
    }
}
