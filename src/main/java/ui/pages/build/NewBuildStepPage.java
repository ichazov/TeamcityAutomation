package ui.pages.build;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import ui.elements.RunnerTypeElement;

import static com.codeborne.selenide.Selenide.$;

public class NewBuildStepPage extends BaseBuildPage {
    private static final String NEW_BUILD_STEP_MENU_URL = "/admin/editRunType.html?id=buildType:%s";
    private static final String RUNNER_ITEM = RunnerTypeElement.getRUNNER_ITEM_ROOT();

    public static NewBuildStepPage open(String buildType) {
        var page = Selenide.open(
          String.format(NEW_BUILD_STEP_MENU_URL, buildType), NewBuildStepPage.class
        );
        $(MAIN_CONTENT_ROOT).shouldBe(Condition.visible);
        return page;
    }

    public void selectRunner(String runner) {
        $(MAIN_CONTENT_ROOT).$(String.format(RUNNER_ITEM, runner)).shouldBe(Condition.clickable)
                .click(ClickOptions.usingJavaScript());
    }
}
