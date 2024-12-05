package ui.pages.createproject;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;

public class CreateProjectPage extends BaseCreateProjectPage {
    public static final String SHOW_MODE_PARAMETER = "createProjectMenu";
    private static final By REPOSITORY_URL_FIELD = By.name("url");

    public static CreateProjectPage open(String projectId) {
        return Selenide.open(
                String.format(CREATE_OBJECT_MENU_URL, projectId, SHOW_MODE_PARAMETER), CreateProjectPage.class
        );
    }

    public CreateProjectFromUrlPage createFromRepoUrl(String repoUrl) {
        enterText(REPOSITORY_URL_FIELD, repoUrl);
        clickElement(PROCEED_BUTTON);
        return Selenide.page(CreateProjectFromUrlPage.class);
    }
}
