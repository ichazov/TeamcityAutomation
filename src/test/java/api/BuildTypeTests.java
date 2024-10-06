package api;

import api.enums.Endpoint;
import api.models.BuildType;
import api.models.Project;
import api.models.User;
import api.requests.checked.CheckedBase;
import api.spec.Specifications;
import org.testng.annotations.Test;

import java.util.concurrent.atomic.AtomicReference;

import static api.generators.TestDataGenerator.generate;
import static io.qameta.allure.Allure.step;

public class BuildTypeTests extends BaseTest {
    @Test(description = "User should be able to create build type")
    public void verifyUserIsAbleToCreateBuildType() {
        User user = generate(User.class);
        Project project = generate(Project.class);
        BuildType buildType = generate(BuildType.class);
        AtomicReference<String> projectId = new AtomicReference<>("");
        AtomicReference<String> buildTypeId = new AtomicReference<>("");

        step("Create user", () -> {
            CheckedBase<User> requester = new CheckedBase<>(Specifications.superUserSpec(), Endpoint.USERS);
            requester.create(user);
        });
        step("Create project as a user", () -> {
            CheckedBase<Project> requester = new CheckedBase<>(Specifications.authSpec(user), Endpoint.PROJECTS);
            projectId.set(requester.create(project).getId());
        });

        CheckedBase<BuildType> requester = new CheckedBase<>(Specifications.authSpec(user), Endpoint.BUILD_TYPES);
        buildType.setProject(Project.builder().id(projectId.get()).locator(null).build());

        step("Create build type as a user", () -> {
            buildTypeId.set(requester.create(buildType).getId());
        });
        step("Verify buildType is created with correct data", () -> {
            String createdBuildTypeName = requester.read(buildTypeId.get()).getName();
            softly.assertEquals(buildType.getName(), createdBuildTypeName, "Incorrect build type name");
        });
    }

    @Test(description = "User should not be able to create build type with existing id")
    public void verifyDuplicatedBuildTypeIdIsNotAllowed() {

    }

    @Test(description = "Project admin should be able to create build type for their project")
    public void verifyAdminIsAbleToCreateBuildTypeForTheirProject() {

    }

    @Test(description = "Project admin should not be able to create build type for another user's project")
    public void verifyAdminIsNotAbleToCreateBuildTypeForAnotherUsersProject() {

    }
}
