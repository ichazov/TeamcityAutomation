package api.requests.unchecked;

import api.enums.Endpoint;
import api.models.BaseModel;
import api.requests.CrudOperations;
import api.requests.Request;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UncheckedBase extends Request implements CrudOperations<Response, Response> {
    public UncheckedBase(RequestSpecification spec, Endpoint endpoint) {
        super(spec, endpoint);
    }

    @Override
    public Response create(BaseModel model) {
        return RestAssured
                .given()
                .spec(spec)
                .body(model)
                .post(endpoint.getUrl());
    }

    @Override
    public Response read(String parameter) {
        return RestAssured
                .given()
                .spec(spec)
                .get(endpoint.getUrl() + "/" + parameter);
    }

    @Override
    public Response update(String parameter, BaseModel model) {
        return RestAssured
                .given()
                .body(model)
                .spec(spec)
                .put(endpoint.getUrl() + "/" + parameter);
    }

    @Override
    public Response delete(String parameter) {
        return RestAssured
                .given()
                .spec(spec)
                .delete(endpoint.getUrl() + "/" + parameter);
    }
}
