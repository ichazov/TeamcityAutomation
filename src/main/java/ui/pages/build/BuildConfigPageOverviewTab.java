package ui.pages.build;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.elements.BuildResultElement;

import java.time.Duration;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.Wait;

public class BuildConfigPageOverviewTab extends BaseBuildConfigPage {
    private static final By ROOT = By.className("OverviewTab__tabs--HS");
    private static final By BUILD_RESULT_ITEM = BuildResultElement.getBUILD_RESULT_ITEM_ROOT();

    public static BuildConfigPageOverviewTab open(String buildId, String state) {
        return Selenide.open(
                String.format(BUILD_CONFIGURATION_URL + "/%s?state=%s", buildId, state),
                BuildConfigPageOverviewTab.class
        );
    }

    public static BuildConfigPageOverviewTab open() {
        return Selenide.page(BuildConfigPageOverviewTab.class);
    }

    public boolean hasRunningBuilds() {
        getInteractableElement(ROOT, BUILD_RESULT_ITEM);
        return !getPageElements($(ROOT).$$(BUILD_RESULT_ITEM)
                .shouldBe(CollectionCondition.empty, Duration.ofSeconds(30)), BuildResultElement::new)
                .isEmpty();
    }

    public List<BuildResultElement> getBuildResults() {
        getInteractableElement(ROOT, BUILD_RESULT_ITEM);
        return getPageElements($(ROOT).$$(BUILD_RESULT_ITEM), BuildResultElement::new).stream()
                .sorted(Comparator.comparing(buildResultElement -> buildResultElement.getBuildResultNumber().getText()))
                .collect(Collectors.toList());
    }
}
