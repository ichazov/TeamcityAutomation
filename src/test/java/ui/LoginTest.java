package ui;

import base.BaseUiTest;
import com.codeborne.selenide.Condition;
import org.testng.annotations.Test;
import ui.components.HeaderComponent;
import ui.pages.login.LoginPage;
import ui.pages.mainpanel.FavoriteProjectsPage;

import static api.enums.Endpoint.USERS;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.url;

public class LoginTest extends BaseUiTest {
    @Test(enabled = true, description = "user should be able to login with valid credentials")
    public void verifyUserCanLoginWithValidCredentials() {
        testData.getUser().setRoles(projectAdminRoles);
        superUserCheckedRequests.getRequest(USERS).create(testData.getUser());

        LoginPage.open().login(testData.getUser());
        FavoriteProjectsPage.open();

        softly.assertThat(url())
                .contains("/favorite/projects")
                .doesNotContain("/login.html");

        $(LoginPage.getROOT()).shouldNotBe(Condition.visible);
    }

    @Test(enabled = true, description = "user should not be able to login with invalid password")
    public void verifyUserCannotLoginWithInvalidPassword() {
        testData.getUser().setRoles(projectAdminRoles);
        superUserCheckedRequests.getRequest(USERS).create(testData.getUser());
        testData.getUser().setPassword("invalid_pwd");
        LoginPage.open().login(testData.getUser());

        softly.assertThat(url()).contains("/login.html");
        $(LoginPage.getROOT()).shouldBe(Condition.visible);
        $(LoginPage.getERROR_MESSAGE()).shouldBe(Condition.visible);
    }

    @Test(enabled = true, description = "user should not be able to login with empty username")
    public void verifyUserCannotLoginWithEmptyUsername() {
        testData.getUser().setRoles(projectAdminRoles);
        superUserCheckedRequests.getRequest(USERS).create(testData.getUser());
        testData.getUser().setUsername("");
        LoginPage.open().login(testData.getUser());

        softly.assertThat(url()).contains("/login.html");
        $(LoginPage.getROOT()).shouldBe(Condition.visible);
        $(LoginPage.getERROR_MESSAGE()).shouldBe(Condition.visible);
    }

    @Test(enabled = true, description = "user should not be able to login with empty password")
    public void verifyUserCannotLoginWithEmptyPassword() {
        testData.getUser().setRoles(projectAdminRoles);
        superUserCheckedRequests.getRequest(USERS).create(testData.getUser());
        testData.getUser().setPassword("");
        LoginPage.open().login(testData.getUser());

        softly.assertThat(url()).contains("/login.html");
        $(LoginPage.getROOT()).shouldBe(Condition.visible);
        $(LoginPage.getERROR_MESSAGE()).shouldBe(Condition.visible);
    }

    @Test(enabled = true, description = "user should not access protected page after logout")
    public void verifyLoggedOutUserCannotAccessProtectedPages() {
        testData.getUser().setRoles(projectAdminRoles);
        superUserCheckedRequests.getRequest(USERS).create(testData.getUser());
        LoginPage.open().login(testData.getUser());
        HeaderComponent.init().logout();
        open("/favorite/projects?mode=builds");

        softly.assertThat(url()).contains("/login.html");
        $(LoginPage.getROOT()).shouldBe(Condition.visible);
    }
}
