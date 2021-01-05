package eu.ldob.lpm.be.converter;

import java.util.ArrayList;
import java.util.List;

public abstract class ModelConverter<Q, M, S> implements IModelConverter<Q, M, S> {

    public abstract M requestToModel(Q request);

    public abstract S modelToResponse(M model);

    public List<M> requestToModelList(List<Q> requestList) {
        List<M> model = new ArrayList<>();

        for(Q request : requestList) {
            model.add(requestToModel(request));
        }

        return model;
    }

    public List<S> modelToResponseList(List<M> modelList) {
        List<S> response = new ArrayList<>();

        for(M model : modelList) {
            response.add(modelToResponse(model));
        }

        return response;
    }

}
