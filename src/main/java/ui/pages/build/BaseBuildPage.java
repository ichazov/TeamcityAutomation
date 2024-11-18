package ui.pages.build;

import org.openqa.selenium.By;
import ui.pages.BasePage;

public abstract class BaseBuildPage extends BasePage {
    protected static final By ROOT = By.className("buildTypeContent");
    protected static final By TITLE = By.className("pc-title");

    protected BaseBuildPage() {
        super(ROOT);
    }
}
