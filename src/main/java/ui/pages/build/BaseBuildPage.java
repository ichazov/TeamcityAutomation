package ui.pages.build;

import org.openqa.selenium.By;
import ui.pages.BasePage;

public abstract class BaseBuildPage extends BasePage {
    protected static final By ROOT = By.xpath("//main[@id='main-content-tag']");

    protected BaseBuildPage() {
        super(ROOT);
    }
}
