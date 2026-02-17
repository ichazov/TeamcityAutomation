package api;

import api.factories.BuildTypeFactory;
import api.factories.UserFactory;
import api.models.*;
import api.requests.checked.CheckedRequests;
import api.requests.unchecked.UncheckedBase;
import api.spec.Specifications;
import base.BaseApiTest;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static api.enums.Endpoint.*;
public class BuildTypeTests extends BaseApiTest {
    @Test(description = "PROJECT_ADMIN not be able to create build type with existing id")
    public void verifyDuplicatedBuildTypeIdIsNotAllowed() {
        testData.getUser().setRoles(projectAdminRoles);
        CheckedRequests userCheckedRequests = new CheckedRequests(Specifications.authSpec(testData.getUser()));
        BuildType buildTypeWithDuplicateId = BuildType.builder().id(testData.getProject().getId()).build();

        superUserCheckedRequests.getRequest(USERS).create(testData.getUser());
        userCheckedRequests.<Project>getRequest(PROJECTS).create(testData.getProject());
        userCheckedRequests.getRequest(BUILD_TYPES).create(testData.getBuildType());

        new UncheckedBase(Specifications.authSpec(testData.getUser()), BUILD_TYPES)
                .create(buildTypeWithDuplicateId)
                .then().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test(description = "PROJECT_ADMIN should be able to create build type for their project")
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

    @Test(description = "PROJECT_ADMIN should be able to create build type for another user's project")
    public void verifyAdminIsAbleToCreateBuildTypeForAnotherUsersProject() {
        testData.getUser().setRoles(projectAdminRoles);
        CheckedRequests userCheckedRequests = new CheckedRequests(Specifications.authSpec(testData.getUser()));
        CheckedRequests adminCheckedRequests = new CheckedRequests(Specifications.authSpec(testData.getUser()));

        superUserCheckedRequests.getRequest(USERS).create(testData.getUser());
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
