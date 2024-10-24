package api.requests.checked;

import api.enums.Endpoint;
import api.generators.TestDataStorage;
import api.models.BaseModel;
import api.requests.CrudOperations;
import api.requests.Request;
import api.requests.unchecked.UncheckedBase;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;

@SuppressWarnings("unchecked")
public final class CheckedBase<T extends BaseModel> extends Request implements CrudOperations {
    private final UncheckedBase uncheckedBase;

    public CheckedBase(RequestSpecification spec, Endpoint endpoint) {
        super(spec, endpoint);
        this.uncheckedBase = new UncheckedBase(spec, endpoint);
    }

    @Override
    public T create(BaseModel model) {
        var m = (T) uncheckedBase
                .create(model)
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .extract().as(endpoint.getModelClass());

        TestDataStorage.getInstance().addToStorage(endpoint, m);
        return m;
    }

    @Override
    public T read(String parameter) {
        return (T) uncheckedBase
                .read(parameter)
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .extract().as(endpoint.getModelClass());
    }

    @Override
    public T update(String parameter, BaseModel model) {
        return (T) uncheckedBase
                .update(parameter, model)
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .extract().as(endpoint.getModelClass());
    }

    @Override
    public Object delete(String parameter) {
        return uncheckedBase
                .delete(parameter)
                .then().assertThat().statusCode(HttpStatus.SC_NO_CONTENT)
                .extract().asString();
    }
}
