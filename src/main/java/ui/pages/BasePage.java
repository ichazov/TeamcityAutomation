package ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public abstract class BasePage {
    private By rootElement;

    protected BasePage(By locator) {
        this.rootElement = locator;
    }

//    protected void setRoot(By locator) {
//        this.root = locator;
//    }

//    protected By getRootElement() {
//        return rootElement;
//    }

    private SelenideElement getInteractableElement(By locator) {
        return $(rootElement).$(locator).shouldBe(Condition.interactable, Duration.ofSeconds(15));
    }

    protected void clickElement(By locator) {
        getInteractableElement(locator).click();
    }

    protected void enterText(By locator, String text) {
        getInteractableElement(locator).val(text);
    }
}
