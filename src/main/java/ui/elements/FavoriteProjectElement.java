package ui.elements;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import org.openqa.selenium.By;

@Getter
public class FavoriteProjectElement extends BasePageElement {
    @Getter
    private static final By FAVORITE_PROJECT_ITEM_ROOT = By.cssSelector("[role='heading']");
    private static final By PROJECT_NAME = By.cssSelector("[data-test='ring-link']");
    private String projectName;

    public FavoriteProjectElement(SelenideElement element) {
        super(element);
        this.projectName = find(PROJECT_NAME).getText();
    }
}
