package ui.elements;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import org.openqa.selenium.By;

@Getter
public class RunnerTypeElement extends BasePageElement {
    @Getter
    private static final By RUNNER_ITEM_ROOT = By.className("SelectBuildRunners__listItem--r8");
    private static final By RUNNER_NAME = By.className("SelectBuildRunners__title--Vf");
    private SelenideElement runnerName;
//    private SelenideElement runnerItem;

    public RunnerTypeElement(SelenideElement element) {
        super(RUNNER_ITEM_ROOT, element);
        this.runnerName = find(RUNNER_NAME);
    }
}
