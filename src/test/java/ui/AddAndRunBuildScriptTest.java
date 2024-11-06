package ui;

import api.models.Project;
import api.requests.checked.CheckedRequests;
import api.spec.Specifications;
import base.BaseUiTest;
import org.testng.annotations.Test;
import ui.pages.build.NewBuildStepPage;
import ui.pages.login.LoginPage;

import static api.enums.Endpoint.*;
import static io.qameta.allure.Allure.step;

public class AddAndRunBuildScriptTest extends BaseUiTest {
    @Test(description = "user should be able to add build script to build step and run it")
    public void verifyUserCanAddAndRunBuildScript() {
        CheckedRequests userCheckedRequests = new CheckedRequests(Specifications.authSpec(testData.getUser()));

        testData.getUser().setRoles(projectAdminRoles);
        superUserCheckedRequests.getRequest(USERS).create(testData.getUser());
        userCheckedRequests.<Project>getRequest(PROJECTS).create(testData.getProject());
        userCheckedRequests.getRequest(BUILD_TYPES).create(testData.getBuildType());
        LoginPage.open().login(testData.getUser());
        NewBuildStepPage.open(testData.getBuildType().getId())
                .selectRunnerByName(".net");
        System.out.println("stop");
        step("create build config");
        step("open 'new build step' page");
        step("get runner type elements");
        step("select cmd");
        step("setup new cmd build step: 'id', 'custom script' and click 'save'");
        step("run build");
        step("verify results");
    }
}
