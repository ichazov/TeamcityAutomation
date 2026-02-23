package ui;

import api.models.BuildType;
import api.models.Project;
import api.requests.checked.CheckedRequests;
import api.spec.Specifications;
import base.BaseUiTest;
import org.testng.annotations.Test;
import ui.components.BuildConfigPageHeaderComponent;
import ui.elements.BuildResultElement;
import ui.pages.build.BuildConfigPageOverviewTab;
import ui.pages.build.NewBuildStepCommandLinePage;
import ui.pages.build.NewBuildStepPage;
import ui.pages.login.LoginPage;

import java.util.List;

import static api.enums.Endpoint.*;

public class AddAndRunBuildScriptTest extends BaseUiTest {
    @Test(description = "user should be able to add build script to build step and run it")
    public void verifyUserCanAddAndRunBuildScript() {
        CheckedRequests userCheckedRequests = new CheckedRequests(Specifications.authSpec(testData.getUser()));

        testData.getUser().setRoles(projectAdminRoles);
        testData.getBuildType().setSteps(null);
        superUserCheckedRequests.getRequest(USERS).create(testData.getUser());
        userCheckedRequests.<Project>getRequest(PROJECTS).create(testData.getProject());
        userCheckedRequests.<BuildType>getRequest(BUILD_TYPES).create(testData.getBuildType());

        LoginPage.open()
                .login(testData.getUser());
        NewBuildStepPage.open(testData.getBuildType().getId())
                .selectRunner("simpleRunner");
        NewBuildStepCommandLinePage.open()
                .setupBuildStep("echo 'Hello, world!'");

        BuildConfigPageOverviewTab runningBuildConfigPage = BuildConfigPageOverviewTab.open(
                testData.getBuildType().getId(),
                "running"
        );

        BuildConfigPageHeaderComponent.init()
                .runBuild();

        String runningBuildId = runningBuildConfigPage.getBuildResults().get(0).getBuildResultNumber().getText();
        boolean hasRunningBuilds = runningBuildConfigPage.hasRunningBuilds();

        softly.assertThat(hasRunningBuilds)
                .withFailMessage("Has running builds")
                .isFalse();

        List<BuildResultElement> successfulBuilds = BuildConfigPageOverviewTab.open(
                testData.getBuildType().getId(),"successful")
                        .getBuildResults();

        softly.assertThatList(successfulBuilds)
                .withFailMessage("Build %s wasn't successful", runningBuildId)
                .anyMatch(b -> b.getBuildResultNumber().getText().equalsIgnoreCase(runningBuildId));
    }
}
