# TeamCity Automation - Context

## Overview
Java 17 Maven project for TeamCity API and UI automation. It combines REST-assured API tests with Selenide UI tests, using TestNG and explicit model factories for test data plus shared cleanup utilities.

## Tech Stack
- Java 17, Maven
- REST-assured 6.x for API requests
- Selenide 7.x + Selenide TestNG for UI
- TestNG for test framework
- Allure Selenide (test reporting)
- Lombok for model boilerplate
- Jackson for JSON
- AssertJ for assertions

## Project Structure
- `src/main/java/api`: API client, models, factories, and specs
  - `api/spec`: request specs, host selection
  - `api/requests`: checked/unchecked API request wrappers
  - `api/models`: DTOs for TeamCity entities (Project, BuildType, User, Roles, etc.)
  - `api/factories`: explicit builders for test data objects
  - `api/generators`: random data helper + test data storage cleanup
- `src/main/java/ui`: UI page objects and elements for TeamCity web UI
- `src/test/java`: TestNG tests
  - `base`: base test classes for shared setup/teardown
  - `api`: API test suites
  - `ui`: UI test suites
- `src/main/resources/config.properties`: env/config values

## Configuration
Config is loaded from `src/main/resources/config.properties` via `api.config.Config`.
- `environment=local|remote` controls host selection in `api.spec.HostManager`
- `remote.host` and `local.host` specify TeamCity host
- `superUserToken` used for superuser API access
- `selenoidRemote` for remote Selenide session
- `browserChrome`, `browserFirefox` used by UI setup

## API Layer
- `api.spec.Specifications` builds REST-assured specs:
  - `superUserSpec()` uses token auth in base URL
  - `authSpec(User)` uses basic auth with user credentials
  - `unauthSpec()` for unauthenticated calls
- `api.requests.checked.CheckedBase` and `CheckedRequests` wrap endpoints with response validation
- `api.requests.unchecked.UncheckedBase` and `UncheckedRequests` allow raw status assertions
- `api.enums.Endpoint` defines endpoint URL and associated model class

## Test Data Generation
- `api.factories` create explicit model instances via Lombok builders
- `api.generators.RandomData` provides random string helpers
- `api.models.TestData` aggregates Project, User, BuildType, Roles for tests

## Data Cleanup
- `api.generators.TestDataStorage` tracks created entity IDs/locators by endpoint
- `BaseTest.tearDown()` clears storage after each test, deleting created entities via API

## Base Tests
- `BaseTest` initializes `SoftAssertions`, builds `TestData` via factory, and cleans storage
- `BaseUiTest` configures Selenide based on `environment` and closes driver per test
- `BaseApiTest` is a thin wrapper over `BaseTest`

## Notable Tests
- `src/test/java/api/BuildTypeTests.java` validates build type creation and duplication rules
- `src/test/java/ui/CreateProjectTest.java` creates projects via UI and validates errors

## Running
- Tests are driven by TestNG; typical entry is `mvn test`
- Requires JDK 17 (set `JAVA_HOME` accordingly)
- Ensure `config.properties` matches the target TeamCity environment and credentials
