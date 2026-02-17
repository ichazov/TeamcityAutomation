package base;

import api.factories.*;
import api.generators.TestDataStorage;
import api.models.Roles;
import api.models.TestData;
import api.requests.checked.CheckedRequests;
import api.spec.Specifications;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.List;

import static api.enums.UserRoles.*;

@SuppressWarnings("all")
public class BaseTest {
    protected SoftAssertions softly;
    protected CheckedRequests superUserCheckedRequests = new CheckedRequests(Specifications.superUserSpec());
    protected TestData testData;
    protected Roles projectAdminRoles = new RolesFactory().create(List.of(PROJECT_ADMIN.getRole()));

    @BeforeMethod(alwaysRun = true)
    protected void setUp() {
        softly = new SoftAssertions();
        testData = new TestDataFactory().create();
    }

    @AfterMethod(alwaysRun = true)
    protected void tearDown() {
        softly.assertAll();
        TestDataStorage.getInstance().clearStorage();
    }
}
