package ui;

import org.testng.annotations.Test;

import static io.qameta.allure.Allure.step;

public class AddAndRunBuildScriptTest {
    @Test(description = "user should be able to add build script to build step and run it")
    public void verifyUserCanAddAndRunBuildScript() {
        step("create build config");
        step("open 'new build step' page");
        step("get runner type elements");
        step("select cmd");
        step("setup new cmd build step: 'id', 'custom script' and click 'save'");
        step("run build");
        step("verify results");
    }
}
