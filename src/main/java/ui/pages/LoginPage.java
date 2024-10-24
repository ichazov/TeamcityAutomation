package ui.pages;

import api.models.User;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {
    public static final String LOGIN_URL = "/login.html";
    private static final By ROOT = By.id("loginForm");
    private static final By USERNAME_FIELD = By.id("username");
    private static final By PASSWORD_FIELD = By.id("password");
    private static final By LOGIN_BUTTON = By.className("loginButton");

    public LoginPage() {
        super(ROOT);
    }

    public static LoginPage open() {
        return Selenide.open(LOGIN_URL, LoginPage.class);
    }

    public ProjectsPage login(User user) {
        enterText(USERNAME_FIELD, user.getUsername());
        enterText(PASSWORD_FIELD, user.getPassword());
        clickElement(LOGIN_BUTTON);
        return Selenide.page(ProjectsPage.class);
    }
}


