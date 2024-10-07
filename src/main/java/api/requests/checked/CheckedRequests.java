package api.requests.checked;

import api.enums.Endpoint;
import api.models.BaseModel;
import api.requests.unchecked.UncheckedBase;
import io.restassured.specification.RequestSpecification;

import java.util.Arrays;
import java.util.EnumMap;

public class CheckedRequests {
    private final EnumMap<Endpoint, CheckedBase> requests = new EnumMap<>(Endpoint.class);

    public CheckedRequests(RequestSpecification spec) {
        Arrays.asList(Endpoint.values())
                .forEach(e -> requests.put(e, new CheckedBase(spec, e)));
    }

    public <T extends BaseModel> CheckedBase<T> getRequest(Endpoint endpoint) {
        return (CheckedBase<T>) requests.get(endpoint);
    }
}
