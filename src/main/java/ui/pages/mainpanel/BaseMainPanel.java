package ui.pages.mainpanel;

import org.openqa.selenium.By;
import ui.pages.BasePage;

public class BaseMainPanel extends BasePage {
    private static final By ROOT = By.className("MainPanel__content--lE");

    protected BaseMainPanel() {
        super(ROOT);
    }
}
