package ui.elements;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

public class ProjectElement extends BasePageElement {
    private By ROOT = By.id("");
    private SelenideElement projectName = "";
    private SelenideElement projectUrl = "";
    private SelenideElement button = "";

    public ProjectElement(SelenideElement element, SelenideElement projectName, SelenideElement projectUrl,
                          SelenideElement button) {
        super(element);
        this.projectName = projectName;
        this.projectUrl = projectUrl;
        this.button = button;
    }
}
