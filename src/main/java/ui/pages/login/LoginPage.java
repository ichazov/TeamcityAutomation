package ui.pages.login;

import api.models.User;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import lombok.Getter;
import org.openqa.selenium.By;
import ui.pages.BasePage;
import ui.pages.mainpanel.FavoriteProjectsPage;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends BasePage {
    private static final String PAGE_URL = "/login.html";
    @Getter
    private static final By ROOT = By.id("loginForm");
    private static final By FIELD_USERNAME = By.id("username");
    private static final By FIELD_PASSWORD = By.id("password");
    private static final By BUTTON_LOGIN = By.className("loginButton");
    @Getter
    private static final By ERROR_MESSAGE = By.id("errorMessage");

    private LoginPage() {
        super(ROOT);
    }

    public static LoginPage open() {
        var page = Selenide.open(PAGE_URL, LoginPage.class);
        $(ROOT).shouldBe(Condition.visible);
        return page;
    }

    public FavoriteProjectsPage login(User user) {
        enterText(FIELD_USERNAME, user.getUsername());
        enterText(FIELD_PASSWORD, user.getPassword());
        clickElement(BUTTON_LOGIN);
        return Selenide.page(FavoriteProjectsPage.class);
    }
}


