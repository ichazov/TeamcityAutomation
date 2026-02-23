package ui.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import lombok.Getter;
import org.openqa.selenium.By;
import ui.pages.BasePage;
import ui.pages.login.LoginPage;

import static com.codeborne.selenide.Selenide.$;

public class HeaderComponent extends BasePage {
    @Getter
    private static final By ROOT = By.className("react-header");
    private static final By USER_MENU_ICON = By.cssSelector("[data-hint-container-id='header-user-menu']");
    private static final By USER_MENU_POPUP = By.cssSelector("[data-test='ring-popup']");
    private static final By USER_MENU_OPTION_LOGOUT = By.linkText("Logout");

    private HeaderComponent() {
        super(ROOT);
    }

    public static HeaderComponent init() {
        return Selenide.page(HeaderComponent.class);
    }

    public LoginPage logout() {
        clickElement(USER_MENU_ICON);
        $(USER_MENU_POPUP).$(USER_MENU_OPTION_LOGOUT).shouldBe(Condition.clickable).click();
        return Selenide.page(LoginPage.class);
    }
}
