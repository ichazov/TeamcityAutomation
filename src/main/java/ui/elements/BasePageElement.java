package ui.elements;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ui.Interactable;

public abstract class BasePageElement implements Interactable {
    private SelenideElement element;

    protected BasePageElement(SelenideElement element) {
        this.element = element;
    }

    protected SelenideElement find(By locator) {
        return element.$(locator);
    }

//    protected SelenideElement find() {
//        return getInteractableElement(elementLocator);
//    }

//    protected ElementsCollection findAll(By locator) {
//        return $(elementLocator).$$(locator);
//    }
}
