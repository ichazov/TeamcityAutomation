package ui.pages.build;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.elements.RunnerTypeElement;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.Wait;

public class NewBuildStepPage extends BaseBuildPage {
    private static final String NEW_BUILD_STEP_MENU_URL = "/admin/editRunType.html?id=buildType:%s";
    private static By runnerItem;

    public NewBuildStepPage() {
        super();
        runnerItem = RunnerTypeElement.getRUNNER_ITEM_ROOT();
    }

    public static NewBuildStepPage open(String buildType) {
        var page = Selenide.open(
          String.format(NEW_BUILD_STEP_MENU_URL, buildType), NewBuildStepPage.class
        );

        Wait()
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(ROOT));

        return page;
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
        getInteractableElement(ROOT, runnerItem);
        return getPageElements($(ROOT).$$(runnerItem),
                RunnerTypeElement::new
        );
    }
}
