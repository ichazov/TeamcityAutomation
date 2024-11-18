package ui.elements;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import org.openqa.selenium.By;

@Getter
public class RunnerTypeElement extends BasePageElement {
    @Getter
    private static final By RUNNER_ITEM_ROOT = By.className("SelectBuildRunners__listItem--r8");
    private static final By RUNNER_NAME = By.className("SelectBuildRunners__title--Vf");
    private final SelenideElement runnerName;

    public RunnerTypeElement(SelenideElement element) {
        super(element);
        this.runnerName = find(RUNNER_NAME);
    }
}
