package ui.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ui.Interactable;
import ui.elements.BasePageElement;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class BasePage implements Interactable {
    private By rootElement;

    protected BasePage(By locator) {
        this.rootElement = locator;
    }

    protected void clickElement(By locator) {
        getInteractableElement(rootElement, locator).click();
    }

    protected void enterText(By locator, String text) {
        getInteractableElement(rootElement, locator).val(text);
    }

    @SuppressWarnings("deprecation")
    protected <T extends BasePageElement> List<T> getPageElements(
            ElementsCollection elements, Function<SelenideElement, T> mapper) {
        return elements.stream()
                .map(mapper)
                .collect(Collectors.toList());
    }
}
