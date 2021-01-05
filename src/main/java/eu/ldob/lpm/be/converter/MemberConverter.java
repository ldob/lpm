package eu.ldob.lpm.be.converter;

import eu.ldob.lpm.be.model.UserModel;
import eu.ldob.lpm.be.request.MemberRequest;
import eu.ldob.lpm.be.response.MemberResponse;
import org.springframework.stereotype.Component;

@Component
public class MemberConverter extends ModelConverter<MemberRequest, UserModel, MemberResponse> {

    @Override
    public UserModel requestToModel(MemberRequest request) {
        UserModel model = new UserModel();

        model.setId(request.getId());
        model.setUsername(request.getName());

        return model;
    }

    @Override
    public MemberResponse modelToResponse(UserModel model) {
        MemberResponse response = new MemberResponse();

        response.setId(model.getId());
        response.setName(model.getUsername());

        return response;
    }
}
