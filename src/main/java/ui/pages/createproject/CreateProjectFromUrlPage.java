package ui.pages.createproject;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import ui.pages.build.AutoDetectedBuildStepsPage;

public class CreateProjectFromUrlPage extends BaseCreateProjectPage {
    private static final By PROJECT_NAME_FIELD = By.id("projectName");
    private static final By BUILD_CONFIGURATION_NAME_FIELD = By.id("buildTypeName");
    public static final By ERROR_LOCATOR = By.className("error");

    public static CreateProjectFromUrlPage open() {
        return Selenide.page(CreateProjectFromUrlPage.class);
    }

    public AutoDetectedBuildStepsPage setupProject(String projectId, String buildConfigurationName) {
        enterText(PROJECT_NAME_FIELD, projectId);
        enterText(BUILD_CONFIGURATION_NAME_FIELD, buildConfigurationName);
        clickElement(PROCEED_BUTTON);
        return Selenide.page(AutoDetectedBuildStepsPage.class);
    }

    public ElementsCollection getErrors() {
       return getInteractableElement(ERROR_LOCATOR).$$(ERROR_LOCATOR);
    }
}
