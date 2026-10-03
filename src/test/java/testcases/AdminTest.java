package testcases;

import org.testng.annotations.Test;

import pages.AdminPage;
import pages.DashboardPage;
import pages.LoginPage;
import testbase.TestBase;

import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class AdminTest {
	private static final Logger logger = LoggerFactory.getLogger(AdminTest.class);
	WebDriver driver;
	TestBase testbase;
	LoginPage loginpage;
	DashboardPage dashboardpage;
	AdminPage adminpage;
	
  @Test(priority=1,description = "Verify_Admin_In_Admin_Table",groups= {"reg"})
  public void TC07_Verify_Admin_In_Admin_Table() throws InterruptedException {
	  logger.info("Verify TC07_Verify_Admin_In_Admin_Table");
	  String adminName = "Ravi B";
	  String actual =adminpage.getAdminName(adminName);
	  testbase.attachScreenshotToTestNg(driver);
	  Assert.assertEquals(actual, adminName);
  }
  @BeforeMethod(alwaysRun = true)
  public void beforeMethod() throws IOException, InterruptedException {
	  testbase = new TestBase();
	  driver = testbase.getDriverInstance();
	  loginpage = new LoginPage(driver);
	  dashboardpage = new DashboardPage(driver);
	  testbase.attachScreenshotToTestNg(driver);
	  loginpage.loginToApplication("Admin", "admin123");
	  assertEquals(dashboardpage.isDashboardDisplayed(), true);
	  testbase.attachScreenshotToTestNg(driver);
	  adminpage = dashboardpage.clickOnAdmin();
	  testbase.attachScreenshotToTestNg(driver);
  }

  @AfterMethod(alwaysRun = true)
  public void afterMethod() throws InterruptedException {
	  dashboardpage.logout();
	  Assert.assertEquals(loginpage.isLoginPageDisplayed(), true);
	  testbase.attachScreenshotToTestNg(driver);
	  testbase.closeDriverInstance();
  }

}
