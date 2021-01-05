package eu.ldob.lpm.be.converter;

import java.util.List;

public interface IModelConverter<Q, M, S> {

    M requestToModel(Q request);

    S modelToResponse(M model);

    List<M> requestToModelList(List<Q> requestList);

    List<S> modelToResponseList(List<M> modelList);
}
