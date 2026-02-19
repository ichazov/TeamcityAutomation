package api.spec;

import api.config.Config;
import api.models.User;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.preemptive;

public class Specifications {
    private static final RestAssuredConfig LOG_CONFIG =
            RestAssuredConfig.newConfig().logConfig(LogConfig.logConfig().blacklistDefaultSensitiveHeaders());

    private Specifications() {
    }

    private static RequestSpecBuilder requestSpecBuilder() {
        return new RequestSpecBuilder()
                .setBaseUri(String.format("http://%s", HostManager.getHost()))
                .setConfig(LOG_CONFIG)
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON);
    }

    public static RequestSpecification superUserSpec() {
        return requestSpecBuilder()
                .setAuth(preemptive().basic("", Config.getProperty("superUserToken")))
                .build();
    }

    public static RequestSpecification unauthSpec() {
        return requestSpecBuilder().build();
    }

    public static RequestSpecification authSpec(User user) {
        return requestSpecBuilder().setAuth(preemptive().basic(user.getUsername(), user.getPassword())).build();
    }
}
