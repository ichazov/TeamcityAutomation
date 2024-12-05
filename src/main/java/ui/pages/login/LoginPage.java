package ui.pages.login;

import api.models.User;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import ui.pages.BasePage;
import ui.pages.mainpanel.FavoriteProjectsPage;

public class LoginPage extends BasePage {
    private static final String LOGIN_URL = "/login.html";
    private static final By ROOT = By.id("loginForm");
    private static final By USERNAME_FIELD = By.id("username");
    private static final By PASSWORD_FIELD = By.id("password");
    private static final By LOGIN_BUTTON = By.className("loginButton");

    private LoginPage() {
        super(ROOT);
    }

    public static LoginPage open() {
        return Selenide.open(LOGIN_URL, LoginPage.class);
    }

    public FavoriteProjectsPage login(User user) {
        enterText(USERNAME_FIELD, user.getUsername());
        enterText(PASSWORD_FIELD, user.getPassword());
        clickElement(LOGIN_BUTTON);
        return Selenide.page(FavoriteProjectsPage.class);
    }
}


