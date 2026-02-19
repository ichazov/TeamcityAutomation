package api.requests;

import api.models.BaseModel;

public interface CrudOperations<R, D> {
    R create(BaseModel model);
    R read(String id);
    R update(String id, BaseModel model);
    D delete(String id);
}
