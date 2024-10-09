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
        superUserCheckedRequests.getRequest(USERS).create(testData.getUser());
        CheckedRequests userCheckedRequests = new CheckedRequests(Specifications.authSpec(testData.getUser()));

        userCheckedRequests.<Project>getRequest(PROJECTS).create(testData.getProject());
        userCheckedRequests.getRequest(BUILD_TYPES).create(testData.getBuildType());

        String createdBuildTypeName = userCheckedRequests.<BuildType>getRequest(BUILD_TYPES)
                .read(testData.getBuildType().getId())
                .getName();

        softly.assertEquals(testData.getBuildType().getName(), createdBuildTypeName, "Incorrect build type name");
    }

    @Test(description = "User should not be able to create build type with existing id")
    public void verifyDuplicatedBuildTypeIdIsNotAllowed() {
        superUserCheckedRequests.getRequest(USERS).create(testData.getUser());
        CheckedRequests userCheckedRequests = new CheckedRequests(Specifications.authSpec(testData.getUser()));

        userCheckedRequests.<Project>getRequest(PROJECTS).create(testData.getProject());

        BuildType buildTypeWithDuplicateId = generate(List.of(testData.getProject()), BuildType.class, testData.getBuildType().getId());

        userCheckedRequests.getRequest(BUILD_TYPES).create(testData.getBuildType());

        new UncheckedBase(Specifications.authSpec(testData.getUser()), BUILD_TYPES)
                .create(buildTypeWithDuplicateId)
                .then().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test(description = "Project admin should be able to create build type for their project")
    public void verifyAdminIsAbleToCreateBuildTypeForTheirProject() {

    }

    @Test(description = "Project admin should not be able to create build type for another user's project")
    public void verifyAdminIsNotAbleToCreateBuildTypeForAnotherUsersProject() {

    }
}
