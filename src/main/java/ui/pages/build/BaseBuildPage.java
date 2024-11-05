package ui.pages.build;

import org.openqa.selenium.By;
import ui.pages.BasePage;

public abstract class BaseBuildPage extends BasePage {
    private static final By ROOT = By.className("buildTypeContent");
    protected BaseBuildPage() {
        super(ROOT);
    }
}
