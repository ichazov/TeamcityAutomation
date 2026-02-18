package api;

import api.factories.BuildTypeFactory;
import api.factories.ProjectFactory;
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
    @Test(description = "PROJECT_ADMIN should not be able to create build type with existing id")
    public void verifyDuplicatedBuildTypeIdIsNotAllowed() {
        testData.getUser().setRoles(projectAdminRoles);
        CheckedRequests userCheckedRequests = new CheckedRequests(Specifications.authSpec(testData.getUser()));

        superUserCheckedRequests.getRequest(USERS).create(testData.getUser());
        userCheckedRequests.<Project>getRequest(PROJECTS).create(testData.getProject());
        BuildType createdBuildType = userCheckedRequests.<BuildType>getRequest(BUILD_TYPES).create(testData.getBuildType());
        BuildType buildTypeWithDuplicateId = BuildType.builder()
                .id(createdBuildType.getId())
                .name(createdBuildType.getName() + "_duplicate")
                .project(testData.getProject())
                .steps(testData.getBuildType().getSteps())
                .build();

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
        User ownerUser = new UserFactory().create(projectAdminRoles);
        User adminUser = new UserFactory().create(projectAdminRoles);
        Project ownersProject = new ProjectFactory().create();
        BuildType buildTypeForOwnersProject = new BuildTypeFactory().create(ownersProject);

        superUserCheckedRequests.getRequest(USERS).create(ownerUser);
        superUserCheckedRequests.getRequest(USERS).create(adminUser);

        CheckedRequests ownerCheckedRequests = new CheckedRequests(Specifications.authSpec(ownerUser));
        CheckedRequests adminCheckedRequests = new CheckedRequests(Specifications.authSpec(adminUser));

        ownerCheckedRequests.<Project>getRequest(PROJECTS).create(ownersProject);
        adminCheckedRequests.getRequest(BUILD_TYPES).create(buildTypeForOwnersProject);

        String createdBuildTypeName = ownerCheckedRequests.<BuildType>getRequest(BUILD_TYPES)
                .read(buildTypeForOwnersProject.getId())
                .getName();

        softly.assertThat(buildTypeForOwnersProject.getName())
                .withFailMessage(() -> "Incorrect build type name")
                .isEqualTo(createdBuildTypeName);
    }
}
