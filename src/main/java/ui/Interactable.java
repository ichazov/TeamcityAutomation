package ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public interface Interactable {
    default SelenideElement getInteractableElement(By rootLocator, By locator) {
        Duration timeout = Duration.ofMillis(Configuration.timeout);
        SelenideElement root = $(rootLocator).shouldBe(Condition.visible, timeout);
        return root.$(locator).shouldBe(Condition.interactable, timeout);
    }

    default SelenideElement getInteractableElement(By locator) {
        Duration timeout = Duration.ofMillis(Configuration.timeout);
        return $(locator).shouldBe(Condition.interactable, timeout);
    }
}
