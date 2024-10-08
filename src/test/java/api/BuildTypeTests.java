package api;

import api.models.BuildType;
import api.models.Project;
import api.models.User;
import api.requests.checked.CheckedRequests;
import api.requests.unchecked.UncheckedBase;
import api.spec.Specifications;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.util.List;

import static api.enums.Endpoint.*;
import static api.generators.TestDataGenerator.generate;

public class BuildTypeTests extends BaseTest {
    @Test(description = "User should be able to create build type")
    public void verifyUserIsAbleToCreateBuildType() {
        User user = generate(User.class);

        superUserCheckedRequests.getRequest(USERS).create(user);
        CheckedRequests userCheckedRequests = new CheckedRequests(Specifications.authSpec(user));

        Project project = generate(Project.class);

        project = userCheckedRequests.<Project>getRequest(PROJECTS).create(project);

        BuildType buildType = generate(List.of(project), BuildType.class);

        userCheckedRequests.getRequest(BUILD_TYPES).create(buildType);
        String createdBuildTypeName = userCheckedRequests.<BuildType>getRequest(BUILD_TYPES)
                .read(buildType.getId())
                .getName();

        softly.assertEquals(buildType.getName(), createdBuildTypeName, "Incorrect build type name");
    }

    @Test(description = "User should not be able to create build type with existing id")
    public void verifyDuplicatedBuildTypeIdIsNotAllowed() {
        User user = generate(User.class);

        superUserCheckedRequests.getRequest(USERS).create(user);
        CheckedRequests userCheckedRequests = new CheckedRequests(Specifications.authSpec(user));

        Project project = generate(Project.class);

        project = userCheckedRequests.<Project>getRequest(PROJECTS).create(project);

        BuildType firstBuildType = generate(List.of(project), BuildType.class);
        BuildType secondBuildType = generate(List.of(project), BuildType.class, firstBuildType.getId());

        userCheckedRequests.getRequest(BUILD_TYPES).create(firstBuildType);

        new UncheckedBase(Specifications.authSpec(user), BUILD_TYPES)
                .create(secondBuildType)
                .then().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test(description = "Project admin should be able to create build type for their project")
    public void verifyAdminIsAbleToCreateBuildTypeForTheirProject() {

    }

    @Test(description = "Project admin should not be able to create build type for another user's project")
    public void verifyAdminIsNotAbleToCreateBuildTypeForAnotherUsersProject() {

    }
}
