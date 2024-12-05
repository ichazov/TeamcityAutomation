package ui.components;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import ui.pages.BasePage;

public class BuildConfigPageHeaderComponent extends BasePage {
    private static final By ROOT = By.className("BuildTypePageHeader__header--tO");
    private static final By RUN_BUTTON = By.cssSelector("[data-test=run-build]");

    private BuildConfigPageHeaderComponent() {
        super(ROOT);
    }

    public static BuildConfigPageHeaderComponent init() {
        return Selenide.page(BuildConfigPageHeaderComponent.class);
    }

    public void runBuild() {
        clickElement(RUN_BUTTON);
    }
}
