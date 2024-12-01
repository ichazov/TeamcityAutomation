package ui.components;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import ui.pages.BasePage;
import ui.pages.build.BuildConfigPageOverviewTab;

public class BuildConfigPageHeaderComponent extends BasePage {
    private static final By ROOT = By.className("BuildTypePageHeader__header--tO");
    private static final By RUN_BUTTON = By.cssSelector("[data-test=run-build]");

    public BuildConfigPageHeaderComponent() {
        super(ROOT);
    }

    public static BuildConfigPageHeaderComponent init() {
        return Selenide.page(BuildConfigPageHeaderComponent.class);
    }

    public void runBuild() {
        clickElement(RUN_BUTTON);
//        return BuildConfigPageOverviewTab.open();
    }
}
