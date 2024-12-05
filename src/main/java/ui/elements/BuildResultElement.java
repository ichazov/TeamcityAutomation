package ui.elements;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import org.openqa.selenium.By;

@Getter
public class BuildResultElement extends BasePageElement {
    @Getter
    private static final By BUILD_RESULT_ITEM_ROOT = By.className("BuildDetails__container--qM");
    private static final By BUILD_RESULT_STATUS = By.className("LinkWithIcon__text--hJ");
    private static final By BUILD_RESULT_NUMBER = By.className("Build__number--RI");
    private SelenideElement buildResultStatus;
    private SelenideElement buildResultNumber;

    public BuildResultElement(SelenideElement element) {
        super(element);
        this.buildResultStatus = find(BUILD_RESULT_STATUS);
        this.buildResultNumber = find(BUILD_RESULT_NUMBER);
    }
}
