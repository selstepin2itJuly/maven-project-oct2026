package testcases;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import pages.DashboardPage;
import pages.LoginPage;
import pages.PIMPage;
import testbase.TestBase;

public class PIMTest {
    private static final Logger logger = LoggerFactory.getLogger(PIMTest.class);
    WebDriver driver;
    TestBase testbase;
    LoginPage loginpage;
    DashboardPage dashboardpage;
    PIMPage pimpage;

    @Test(priority = 1, description = "Search_Employee_With_Employment_Status", groups = {"reg"})
    public void TC008_Search_Employee_With_Employment_Status() throws InterruptedException {
        logger.info("Starting TC008_Search_Employee_With_Employment_Status");

        String employeeName = "";
        String employmentStatus = "Full-Time Contract";


        pimpage.searchEmployeeByName(employeeName);
        pimpage.selectEmploymentStatus(employmentStatus);
        pimpage.clickSearchButton();

        boolean resultsDisplayed = pimpage.isSearchResultsDisplayed();
        testbase.attachScreenshotToTestNg(driver);
        Assert.assertTrue(resultsDisplayed, "Search results should be displayed");

        boolean employeeFound = pimpage.isEmployeeDisplayedWithStatus(employeeName, employmentStatus);
        Assert.assertTrue(employeeFound, "Employee should be found with the selected employment status");
        logger.info("Employee found with expected employment status");
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() throws IOException, InterruptedException {
        logger.info("Before Method: Initializing browser and logging in");
        testbase = new TestBase();
        driver = testbase.getDriverInstance();
        loginpage = new LoginPage(driver);
        dashboardpage = new DashboardPage(driver);

        testbase.attachScreenshotToTestNg(driver);

        loginpage.loginToApplication("Admin", "admin123");
        assertEquals(dashboardpage.isDashboardDisplayed(), true);

        testbase.attachScreenshotToTestNg(driver);

        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewEmployeeList");
        pimpage = new PIMPage(driver);
        testbase.attachScreenshotToTestNg(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() throws InterruptedException {
        logger.info("After Method: Logging out and closing browser");
        dashboardpage.logout();
        assertEquals(loginpage.isLoginPageDisplayed(), true);
        testbase.attachScreenshotToTestNg(driver);
        testbase.closeDriverInstance();
    }
}
