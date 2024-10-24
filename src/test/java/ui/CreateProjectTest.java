package ui;

import api.enums.Endpoint;
import base.BaseUiTest;
import org.testng.annotations.Test;
import ui.pages.CreateProjectPage;
import ui.pages.LoginPage;

import static io.qameta.allure.Allure.step;

public class CreateProjectTest extends BaseUiTest {
    private static final String REPO_URL = "https://github.com/ichazov/TestRepo";

    @Test(description = "user should be able to create project")
    public void verifyUserCanCreateProject() {
        superUserCheckedRequests.getRequest(Endpoint.USERS).create(testData.getUser());
        LoginPage.open().login(testData.getUser());
        CreateProjectPage.open("_Root")
                .createFromRepoUrl(REPO_URL)
                .setupProject(
                        testData.getProject().getId(),
                        testData.getBuildType().getId()
                );
        System.out.println("stop");
        step("");
        step("");
    }

    @Test(description = "user should not be able to create project without name")
    public void verifyUserCannotCreateProjectWithoutName() {
        step("login as user");
        step("check number of projects");
        step("open 'Create Project' page");
        step("select 'Manually'");
        step("submit project data: project id, description");
        step("check number of projects has not changed");

        step("verify error appears ");

    }
}
