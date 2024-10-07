package api.requests.unchecked;

import api.enums.Endpoint;
import io.restassured.specification.RequestSpecification;

import java.util.Arrays;
import java.util.EnumMap;

public class UncheckedRequests {
    private final EnumMap<Endpoint, UncheckedBase> requests = new EnumMap<>(Endpoint.class);

    public UncheckedRequests(RequestSpecification spec) {
        Arrays.asList(Endpoint.values())
                .forEach(e -> requests.put(e, new UncheckedBase(spec, e)));
    }

    public UncheckedBase getRequest(Endpoint endpoint) {
        return requests.get(endpoint);
    }
}
