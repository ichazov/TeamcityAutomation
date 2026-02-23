package ui.pages.build;

import api.generators.RandomData;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class NewBuildStepCommandLinePage extends BaseBuildPage {
    private static final By NEW_RUNNER_ID_FIELD = By.id("newRunnerId");
    private static final By SAVE_BUTTON = By.className("submitButton");

    public static NewBuildStepCommandLinePage open() {
        return Selenide.page(NewBuildStepCommandLinePage.class);
    }

    public void setupBuildStep(String script) {
        enterText(NEW_RUNNER_ID_FIELD, RandomData.getString());
        $(MAIN_CONTENT_ROOT).$(By.className("CodeMirror")).shouldBe(Condition.interactable);
        Selenide.executeJavaScript(
                "document.querySelector('.CodeMirror').CodeMirror.setValue(arguments[0])", script
        );
        clickElement(SAVE_BUTTON);
    }
}
