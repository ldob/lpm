package eu.ldob.lpm.be.converter;

import eu.ldob.lpm.be.model.ProjectTodoModel;
import eu.ldob.lpm.be.request.TodoRequest;
import eu.ldob.lpm.be.response.TodoResponse;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TodoConverter implements ModelConverter<TodoRequest, ProjectTodoModel, TodoResponse> {

    @Autowired
    MemberConverter memberConverter;

    @Override
    public ProjectTodoModel requestToModel(TodoRequest request) {
        throw new NotYetImplementedException();
    }

    @Override
    public TodoResponse modelToResponse(ProjectTodoModel model) {
        TodoResponse response = new TodoResponse();

        response.setId(model.getId());
        response.setDescription(model.getDescription());
        response.setStatus(model.getStatus());
        response.setDueDate(model.getDueDate());
        response.setAssignedMember(memberConverter.modelToResponse(model.getAssignedUser()));

        return response;
    }
}
