package ui.pages;

import org.openqa.selenium.By;

public abstract class BaseCreateProjectPage extends BasePage {
    protected static final String CREATE_OBJECT_MENU_URL = "/admin/createObjectMenu.html?projectId=%s&showMode=%s";
    private static final By ROOT = By.id("mainContent");
    protected static final By PROCEED_BUTTON = By.className("submitButton");

    protected BaseCreateProjectPage() {
        super(ROOT);
    }
}
