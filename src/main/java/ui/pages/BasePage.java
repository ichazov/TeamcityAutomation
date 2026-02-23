package ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ui.elements.BasePageElement;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$;

public abstract class BasePage {
    protected static final By MAIN_CONTENT_ROOT = By.tagName("main");
    private final By rootElement;

    protected BasePage() {
        this(MAIN_CONTENT_ROOT);
    }

    protected BasePage(By locator) {
        this.rootElement = locator;
    }

    protected void clickElement(By locator) {
        $(rootElement).$(locator).shouldBe(Condition.clickable).click();
    }

    protected void enterText(By locator, String text) {
        $(rootElement).$(locator).shouldBe(Condition.interactable).val(text);
    }

    protected <T extends BasePageElement> List<T> getPageElements(
            ElementsCollection elements, Function<SelenideElement, T> mapper) {
        return elements.stream()
                .map(mapper)
                .collect(Collectors.toList());
    }
}
