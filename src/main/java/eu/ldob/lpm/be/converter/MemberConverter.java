package eu.ldob.lpm.be.converter;

import eu.ldob.lpm.be.model.UserModel;
import eu.ldob.lpm.be.request.MemberRequest;
import eu.ldob.lpm.be.response.MemberResponse;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.stereotype.Component;

@Component
public class MemberConverter implements ModelConverter<MemberRequest, UserModel, MemberResponse> {

    @Override
    public UserModel requestToModel(MemberRequest request) {
        throw new NotYetImplementedException();
    }

    @Override
    public MemberResponse modelToResponse(UserModel model) {
        MemberResponse response = new MemberResponse();

        response.setId(model.getId());
        response.setName(model.getUsername());

        return response;
    }
}
