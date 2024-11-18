package ui.pages.build;

import api.generators.RandomData;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.ByChained;

import static com.codeborne.selenide.Selenide.$;

public class NewBuildStepCommandLinePage extends BaseBuildPage {
    private static final By CUSTOM_SCRIPT_FIELD = By.className("CodeMirror");
    private static final By NEW_RUNNER_ID_FIELD = By.id("newRunnerId");
    private static final By SAVE_BUTTON = By.className("submitButton");

    public static NewBuildStepCommandLinePage open() {
        return Selenide.page(NewBuildStepCommandLinePage.class);
    }

    public void setupBuildStep(String script) {
        enterText(NEW_RUNNER_ID_FIELD, RandomData.getString());
        Selenide.executeJavaScript(
                "arguments[0].value('arguments[1]')",
                $(new ByChained(ROOT, CUSTOM_SCRIPT_FIELD)).toWebElement(), script
        );
//        enterText(CUSTOM_SCRIPT_FIELD, script);
        clickElement(SAVE_BUTTON);
    }
}
