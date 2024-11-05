package ui.elements;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ui.Interactable;

import static com.codeborne.selenide.Selenide.$;

public abstract class BasePageElement implements Interactable {
    private By rootElement;
    private SelenideElement element;

    protected BasePageElement(By locator, SelenideElement element) {
        this.rootElement = locator;
        this.element = element;
    }

    protected SelenideElement find(By locator) {
        return getInteractableElement(rootElement, locator);
    }

    protected ElementsCollection findAll(By locator) {
        return $(rootElement).$$(locator);
    }
}
