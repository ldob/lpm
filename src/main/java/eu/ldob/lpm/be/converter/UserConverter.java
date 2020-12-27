package eu.ldob.lpm.be.converter;

import eu.ldob.lpm.be.model.RoleModel;
import eu.ldob.lpm.be.model.UserModel;
import eu.ldob.lpm.be.model.type.ERole;
import eu.ldob.lpm.be.request.UserRequest;
import eu.ldob.lpm.be.response.UserResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserConverter implements ModelConverter<UserRequest, UserModel, UserResponse> {

    @Override
    public UserModel requestToModel(UserRequest request) {
        UserModel model = new UserModel();

        model.setId(request.getId());
        model.setUsername(request.getUsername());
        model.setEmail(request.getEmail());

        return model;
    }

    @Override
    public UserResponse modelToResponse(UserModel model) {
        UserResponse response = new UserResponse();

        response.setId(model.getId());
        response.setUsername(model.getUsername());
        response.setEmail(model.getEmail());

        List<ERole> roles = new ArrayList<>();
        for(RoleModel r : model.getRoles()) {
            roles.add(r.getName());
        }
        response.setRoles(roles);

        return response;
    }
}
