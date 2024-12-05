package ui.pages.mainpanel;

import org.openqa.selenium.By;
import ui.pages.BasePage;

public abstract class BaseMainPanel extends BasePage {
    protected static final By ROOT = By.className("MainPanel__content--lE");

    protected BaseMainPanel() {
        super(ROOT);
    }
}
