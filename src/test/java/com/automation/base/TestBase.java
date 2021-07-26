package com.automation.base;

import com.automation.base.extentReports.ExtentManager;
import com.automation.base.extentReports.ExtentReporter;
import org.junit.jupiter.api.*;

public class TestBase {

    public static SeleniumUtils seleniumUtils;
    private static ExtentManager extentManager;

    @BeforeAll
    public static void beforeAll() {

        extentManager = new ExtentManager();
    }

    @BeforeEach
    public void beforeEach(TestInfo testInfo) {
        seleniumUtils = new SeleniumUtils((new SeleniumDriver()).getWebDriver());
        ExtentReporter.setExtentTest(extentManager.getExtentReports().createTest(testInfo.getDisplayName()));
    }

    @AfterEach
    public void afterEach() {

        extentManager.getExtentReports().flush();
        seleniumUtils.getWebDriver().quit();
    }

    @AfterAll
    public static void afterAll() {
        extentManager.getExtentReports().flush();
    }


}
