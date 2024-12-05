package ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public interface Interactable {

    default SelenideElement getInteractableElement(By rootLocator, By locator) {
        return $(rootLocator).$(locator).shouldBe(Condition.interactable, Duration.ofSeconds(15));
    }

    default SelenideElement getInteractableElement(By locator) {
        return $(locator).shouldBe(Condition.interactable, Duration.ofSeconds(15));
    }
}
