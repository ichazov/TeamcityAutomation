package base;

import api.config.Config;
import api.spec.HostManager;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;

import java.util.Map;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@SuppressWarnings("all")
public class BaseUiTest extends BaseTest {
    /*Update 'environment' in config.properties before running tests*/
    @BeforeSuite(alwaysRun = true)
    protected void setupUiTest() {
        if (HostManager.isLocal()) {
            setupLocal();
        } else setupRemote();
    }

    @AfterMethod(alwaysRun = true)
    protected void tearDownUiTest() {
        Selenide.closeWebDriver();
    }

    private void setupLocal() {
        Configuration.timeout = 20000;
        Configuration.browser = Config.getProperty("browser.firefox");
        Configuration.baseUrl = "http://" + HostManager.getHost();
        open();
        getWebDriver().manage().window().maximize();
    }

    private void setupRemote() {
        Configuration.timeout = 20000;
        Configuration.browser = Config.getProperty("browser.firefox");
        Configuration.baseUrl = "http://" + HostManager.getHost();
        Configuration.remote = Config.getProperty("host.remote.selenoid");
//        Configuration.browserSize = Config.getProperty("browserSize");
        Configuration.browserCapabilities.setCapability("selenoid:options", Map.of(
                "enableVNC", true,
                "enableLog", true
        ));
    }
}
