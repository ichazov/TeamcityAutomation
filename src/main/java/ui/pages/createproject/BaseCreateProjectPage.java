package ui.pages.createproject;

import org.openqa.selenium.By;
import ui.pages.BasePage;

public abstract class BaseCreateProjectPage extends BasePage {
    protected static final String CREATE_OBJECT_MENU_URL = "/admin/createObjectMenu.html?projectId=%s&showMode=%s";
    protected static final By PROCEED_BUTTON = By.className("submitButton");

    protected BaseCreateProjectPage() {
        super();
    }
}
