package base;

import api.config.Config;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;

import java.util.Map;

import static com.codeborne.selenide.Selenide.open;

@SuppressWarnings("all")
public class BaseUiTest extends BaseTest {

    @BeforeSuite(alwaysRun = true)
    protected void setupUiTest() {
        Configuration.timeout = 20000;
        Configuration.browser = Config.getProperty("browserFirefox");
        Configuration.baseUrl = "http://" + Config.getProperty("host");
        Configuration.remote = Config.getProperty("remote");
//        Configuration.browserSize = Config.getProperty("browserSize");
        Configuration.browserCapabilities.setCapability("selenoid:options", Map.of(
                "enableVNC", true,
                "enableLog", true
        ));
        open();
    }

    @AfterMethod(alwaysRun = true)
    protected void tearDownUiTest() {
        Selenide.closeWebDriver();
    }
}
