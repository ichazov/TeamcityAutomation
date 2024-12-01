package ui.components;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import ui.pages.BasePage;

public class BreadcrumbsComponent extends BasePage {
    private static final By ROOT = By.id("breadcrumbsWrapper");
    private static final By RUN_BUTTON = By.xpath("button[text()='Run']");

    protected BreadcrumbsComponent() {
        super(ROOT);
    }

    public static BreadcrumbsComponent init() {
        return Selenide.page(BreadcrumbsComponent.class);
    }

    public void clickRunButton() {
        clickElement(RUN_BUTTON);
    }
}
