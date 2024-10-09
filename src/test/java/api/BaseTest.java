package api;

import api.generators.TestDataStorage;
import api.models.TestData;
import api.requests.checked.CheckedRequests;
import api.spec.Specifications;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import static api.generators.TestDataGenerator.generate;

public class BaseTest {
    protected SoftAssert softly;
    protected CheckedRequests superUserCheckedRequests = new CheckedRequests(Specifications.superUserSpec());
    protected TestData testData;

    @BeforeMethod(alwaysRun = true)
    void setUp() {
        softly = new SoftAssert();
        testData = generate();
    }

    @AfterMethod(alwaysRun = true)
    void tearDown() {
        softly.assertAll();
        TestDataStorage.getInstance().clearStorage();
    }
}
