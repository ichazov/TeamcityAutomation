package ui.pages.build;

import com.codeborne.selenide.Selenide;
import ui.elements.RunnerTypeElement;

import java.util.List;

public class NewBuildStepPage extends BaseBuildPage {
    private static final String NEW_BUILD_STEP_MENU_URL = "/admin/editRunType.html?id=buildType:%s";

    public static NewBuildStepPage open(String buildType) {
        return Selenide.open(
          String.format(NEW_BUILD_STEP_MENU_URL, buildType), NewBuildStepPage.class
        );
    }

    public void selectRunnerByName(String name) {
        getRunnerTypes().stream()
                .filter(e -> e.getRunnerName().getText().equalsIgnoreCase(name))
                .findAny()
                .orElseThrow()
                .getRunnerName()
                .click();
    }

    private List<RunnerTypeElement> getRunnerTypes() {
        return getPageElements(getInteractableElement(ROOT).$$(RunnerTypeElement.getRUNNER_ITEM_ROOT()),
                RunnerTypeElement::new
        );
    }
}
