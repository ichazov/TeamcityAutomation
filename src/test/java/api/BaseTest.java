package api;

import api.enums.Endpoint;
import api.requests.checked.CheckedRequests;
import api.spec.Specifications;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

public class BaseTest {
    protected SoftAssert softly;
    protected CheckedRequests superUserCheckedRequests = new CheckedRequests(Specifications.superUserSpec());

    @BeforeMethod(alwaysRun = true)
    void setUp() {
        softly = new SoftAssert();
    }

    @AfterMethod(alwaysRun = true)
    void tearDown() {
        softly.assertAll();
    }
}
