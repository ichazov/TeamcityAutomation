package api.spec;

import api.config.Config;
import api.models.User;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.Arrays;

public class Specifications {
    private Specifications() {
    }

    private static RequestSpecBuilder requestSpecBuilder() {
        return new RequestSpecBuilder()
                .addFilters(Arrays.asList(
                        new RequestLoggingFilter(),
                        new ResponseLoggingFilter()))
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON);
    }

    public static RequestSpecification superUserSpec() {
        String uri = String.format("http://%s:%s@%s", "", Config.getProperty("superUserToken"), Config.getProperty("host"));
        return requestSpecBuilder().setBaseUri(uri).build();
    }

    public static RequestSpecification unauthSpec() {
        return requestSpecBuilder().build();
    }

    public static RequestSpecification authSpec(User user) {
        String uri = String.format("http://%s:%s@%s", user.getUsername(), user.getPassword(), Config.getProperty("host"));
        return requestSpecBuilder().setBaseUri(uri).build();
    }
}
