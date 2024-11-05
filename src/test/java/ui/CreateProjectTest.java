package ui;

import api.models.Projects;
import api.requests.checked.CheckedRequests;
import api.spec.Specifications;
import base.BaseUiTest;
import org.testng.annotations.Test;
import ui.pages.createproject.CreateProjectFromUrlPage;
import ui.pages.createproject.CreateProjectPage;
import ui.pages.login.LoginPage;
import ui.pages.mainpanel.FavoriteProjectsPage;

import static api.enums.Endpoint.*;

public class CreateProjectTest extends BaseUiTest {
    private static final String REPO_URL = "https://github.com/ichazov/TestRepo";

    @Test(description = "user should be able to create project")
    public void verifyUserCanCreateProject() {
        testData.getUser().setRoles(projectAdminRoles);
        superUserCheckedRequests.getRequest(USERS).create(testData.getUser());
        LoginPage.open().login(testData.getUser());
        CreateProjectPage.open("_Root")
                .createFromRepoUrl(REPO_URL)
                .setupProject(
                        testData.getProject().getId(),
                        testData.getBuildType().getId()
                );

        softly.assertThat(FavoriteProjectsPage.open().getFavoriteProjects())
                .withFailMessage("No project found")
                .anyMatch(p -> p.getProjectName().getText().equals(testData.getProject().getId()));
    }

    @Test(description = "user should not be able to create project without name")
    public void verifyUserCannotCreateProjectWithoutName() {
        String errorMsg = "Project name must not be empty";
        CheckedRequests userCheckedRequest = new CheckedRequests(Specifications.authSpec(testData.getUser()));

        testData.getUser().setRoles(projectAdminRoles);
        superUserCheckedRequests.getRequest(USERS).create(testData.getUser());
        LoginPage.open().login(testData.getUser());

        int projectsCount = userCheckedRequest
                .<Projects>getRequest(PROJECTSS).read("").getProject().size();

        CreateProjectPage.open("_Root")
                .createFromRepoUrl(REPO_URL)
                .setupProject(
                        "",
                        testData.getBuildType().getId()
                );

        int newProjectsCount = userCheckedRequest
                .<Projects>getRequest(PROJECTSS).read("").getProject().size();

        softly.assertThat(CreateProjectFromUrlPage.open().getErrors())
                .withFailMessage(String.format("%s error message is not displayed", errorMsg))
                .anyMatch(e -> e.getText().equals(errorMsg));

        softly.assertThat(projectsCount)
                .withFailMessage("Project count mismatch")
                .isEqualTo(newProjectsCount);
    }
}
