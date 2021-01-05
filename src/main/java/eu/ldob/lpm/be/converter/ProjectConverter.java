package eu.ldob.lpm.be.converter;

import eu.ldob.lpm.be.model.AssignedProjectModel;
import eu.ldob.lpm.be.model.ProjectModel;
import eu.ldob.lpm.be.request.ProjectRequest;
import eu.ldob.lpm.be.response.ProjectResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProjectConverter extends ModelConverter<ProjectRequest, ProjectModel, ProjectResponse> {

    @Autowired
    MemberConverter memberConverter;

    @Override
    public ProjectModel requestToModel(ProjectRequest request) {
        ProjectModel model = new ProjectModel();

        model.setId(request.getId());
        model.setName(request.getName());
        model.setDescription(request.getDescription());
        model.setPriority(request.getPriority());
        model.setStartDate(request.getStartDate());
        model.setPlannedEndDate(request.getPlannedEndDate());
        model.setEndDate(request.getEndDate());
        model.setResourceBudget(request.getResourceBudget());

        return model;
    }

    @Override
    public ProjectResponse modelToResponse(ProjectModel model) {
        ProjectResponse response = new ProjectResponse();

        response.setId(model.getId());
        response.setName(model.getName());
        response.setDescription(model.getDescription());
        response.setPriority(model.getPriority());
        response.setStartDate(model.getStartDate());
        response.setPlannedEndDate(model.getPlannedEndDate());
        response.setEndDate(model.getEndDate());
        response.setResourceBudget(model.getResourceBudget());
        response.setStatus(model.getLatestStatus());

        for(AssignedProjectModel assignedUser : model.getAssignedUsers()) {
            response.addAssignedMember(assignedUser.getRole(), memberConverter.modelToResponse(assignedUser.getUser()));
        }

        return response;
    }
}
