package api.factories;

import api.models.BuildType;
import api.models.Project;
import api.models.Roles;
import api.models.TestData;
import api.models.User;

public class TestDataFactory {
    private final ProjectFactory projectFactory = new ProjectFactory();
    private final UserFactory userFactory = new UserFactory();
    private final BuildTypeFactory buildTypeFactory = new BuildTypeFactory();

    public TestData create() {
        Project project = projectFactory.create();
        User user = userFactory.create();
        BuildType buildType = buildTypeFactory.create(project);

        return TestData.builder()
                .project(project)
                .user(user)
                .buildType(buildType)
                .build();
    }

    public TestData create(Project project, User user, BuildType buildType, Roles roles) {
        return TestData.builder()
                .project(project)
                .user(user)
                .buildType(buildType)
                .roles(roles)
                .build();
    }
}
