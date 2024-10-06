package api.requests;

import api.models.BaseModel;

public interface CrudOperations {
    Object create(BaseModel model);
    Object read(String id);
    Object update(String id, BaseModel model);
    Object delete(String id);
}
