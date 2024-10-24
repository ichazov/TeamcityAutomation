package ui.pages;

import org.openqa.selenium.By;

public class CreateProjectFromUrlPage extends BaseCreateProjectPage {
    private static final By PROJECT_NAME_FIELD = By.id("projectName");
    private static final By BUILD_CONFIGURATION_NAME_FIELD = By.id("buildTypeName");

    public void setupProject(String projectId, String buildConfigurationName) {
        enterText(PROJECT_NAME_FIELD, projectId);
        enterText(BUILD_CONFIGURATION_NAME_FIELD, buildConfigurationName);
        clickElement(PROCEED_BUTTON);
    }
}
