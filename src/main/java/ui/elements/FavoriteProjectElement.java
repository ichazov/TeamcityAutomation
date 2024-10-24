package ui.elements;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import org.openqa.selenium.By;

@Getter
public class FavoriteProjectElement extends BasePageElement {
    @Getter
    private static final By ROOT = By.cssSelector("[data-test='subproject']");
    private static final By PROJECT_NAME = By.cssSelector("a [title]");
    private SelenideElement projectName;

    public FavoriteProjectElement(SelenideElement element) {
        super(ROOT, element);
        this.projectName = find(PROJECT_NAME);
    }
}
