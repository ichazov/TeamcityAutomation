package ui.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import ui.pages.build.BuildStepsPage;

import static com.codeborne.selenide.Selenide.$$;

public class CreateProjectFromUrlPage extends BaseCreateProjectPage {
    private static final By PROJECT_NAME_FIELD = By.id("projectName");
    private static final By BUILD_CONFIGURATION_NAME_FIELD = By.id("buildTypeName");

    public static CreateProjectFromUrlPage open() {
        return Selenide.page(CreateProjectFromUrlPage.class);
    }

    public BuildStepsPage setupProject(String projectId, String buildConfigurationName) {
        enterText(PROJECT_NAME_FIELD, projectId);
        enterText(BUILD_CONFIGURATION_NAME_FIELD, buildConfigurationName);
        clickElement(PROCEED_BUTTON);
        return Selenide.page(BuildStepsPage.class);
    }

    public ElementsCollection getErrors() {
       return $$(By.className("error"));
    }
}
