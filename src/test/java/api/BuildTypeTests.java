package api;

import api.models.*;
import api.requests.checked.CheckedRequests;
import api.requests.unchecked.UncheckedBase;
import api.spec.Specifications;
import base.BaseApiTest;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.util.List;

import static api.enums.Endpoint.*;
import static api.generators.TestDataGenerator.generate;

public class BuildTypeTests extends BaseApiTest {
    @Test(description = "User should be able to create build type")
    public void verifyUserIsAbleToCreateBuildType() {
        CheckedRequests userCheckedRequests = new CheckedRequests(Specifications.authSpec(testData.getUser()));
        superUserCheckedRequests.getRequest(USERS).create(testData.getUser());
        userCheckedRequests.<Project>getRequest(PROJECTS).create(testData.getProject());
        userCheckedRequests.getRequest(BUILD_TYPES).create(testData.getBuildType());

        String createdBuildTypeName = userCheckedRequests.<BuildType>getRequest(BUILD_TYPES)
                .read(testData.getBuildType().getId())
                .getName();

        softly.assertThat(testData.getBuildType().getName())
                .withFailMessage(() -> "Incorrect build type name")
                .isEqualTo(createdBuildTypeName);
    }

    @Test(description = "User should not be able to create build type with existing id")
    public void verifyDuplicatedBuildTypeIdIsNotAllowed() {
        CheckedRequests userCheckedRequests = new CheckedRequests(Specifications.authSpec(testData.getUser()));
        BuildType buildTypeWithDuplicateId = generate(
                List.of(testData.getProject()), BuildType.class, testData.getBuildType().getId()
        );

        superUserCheckedRequests.getRequest(USERS).create(testData.getUser());
        userCheckedRequests.<Project>getRequest(PROJECTS).create(testData.getProject());
        userCheckedRequests.getRequest(BUILD_TYPES).create(testData.getBuildType());

        new UncheckedBase(Specifications.authSpec(testData.getUser()), BUILD_TYPES)
                .create(buildTypeWithDuplicateId)
                .then().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test(description = "Project admin should be able to create build type for their project")
    public void verifyAdminIsAbleToCreateBuildTypeForTheirProject() {
        testData.getUser().setRoles(projectAdminRoles);
        superUserCheckedRequests.getRequest(USERS).create(testData.getUser());

        CheckedRequests userCheckedRequests = new CheckedRequests(Specifications.authSpec(testData.getUser()));

        userCheckedRequests.<Project>getRequest(PROJECTS).create(testData.getProject());
        userCheckedRequests.getRequest(BUILD_TYPES).create(testData.getBuildType());

        String createdBuildTypeName = userCheckedRequests.<BuildType>getRequest(BUILD_TYPES)
                .read(testData.getBuildType().getId())
                .getName();

        softly.assertThat(testData.getBuildType().getName())
                .withFailMessage(() -> "Incorrect build type name")
                .isEqualTo(createdBuildTypeName);
    }

    @Test(description = "Project admin should be able to create build type for another user's project")
    public void verifyAdminIsAbleToCreateBuildTypeForAnotherUsersProject() {
        User projectAdmin = generate(User.class, projectAdminRoles);
        CheckedRequests userCheckedRequests = new CheckedRequests(Specifications.authSpec(testData.getUser()));
        CheckedRequests adminCheckedRequests = new CheckedRequests(Specifications.authSpec(projectAdmin));

        superUserCheckedRequests.getRequest(USERS).create(testData.getUser());
        superUserCheckedRequests.getRequest(USERS).create(projectAdmin);
        userCheckedRequests.<Project>getRequest(PROJECTS).create(testData.getProject());
        adminCheckedRequests.getRequest(BUILD_TYPES).create(testData.getBuildType());

        String createdBuildTypeName = userCheckedRequests.<BuildType>getRequest(BUILD_TYPES)
                .read(testData.getBuildType().getId())
                .getName();

        softly.assertThat(testData.getBuildType().getName())
                .withFailMessage(() -> "Incorrect build type name")
                .isEqualTo(createdBuildTypeName);
    }
}
