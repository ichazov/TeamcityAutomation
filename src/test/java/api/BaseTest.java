package api;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

public class BaseTest {
    protected SoftAssert softly;

    @BeforeMethod(alwaysRun = true)
    void setUp() {
        softly = new SoftAssert();
    }

    @AfterMethod(alwaysRun = true)
    void tearDown() {
        softly.assertAll();
    }
}
