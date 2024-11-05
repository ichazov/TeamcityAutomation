package api.enums;

import api.models.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Endpoint {
    BUILD_TYPES("/app/rest/buildTypes", BuildType.class),
    USERS("/app/rest/users", User.class),
    PROJECTS("/app/rest/projects", Project.class),
    PROJECTSS("/app/rest/projects", Projects.class);

    private final String url;
    private final Class<? extends BaseModel> modelClass;
}
