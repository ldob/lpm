package eu.ldob.lpm.be.converter;

import eu.ldob.lpm.be.model.WeekModel;
import eu.ldob.lpm.be.request.WeekRequest;
import eu.ldob.lpm.be.response.WeekResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WeekConverter extends ModelConverter<WeekRequest, WeekModel, WeekResponse> {

    @Autowired
    MemberConverter memberConverter;

    @Override
    public WeekModel requestToModel(WeekRequest request) {
        WeekModel model = new WeekModel();

        model.setId(request.getId());
        model.setYear(request.getYear());
        model.setWeek(request.getWeek());

        return model;
    }

    @Override
    public WeekResponse modelToResponse(WeekModel model) {
        WeekResponse response = new WeekResponse();

        response.setId(model.getId());
        response.setYear(model.getYear());
        response.setWeek(model.getWeek());
        response.setFromDate(model.getFromDate());
        response.setToDate(model.getToDate());

        return response;
    }
}
