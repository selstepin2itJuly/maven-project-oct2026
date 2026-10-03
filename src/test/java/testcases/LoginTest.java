package testcases;

import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.LoginPage;
import testbase.TestBase;

import org.testng.annotations.BeforeMethod;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class LoginTest {
	private static final Logger logger = LoggerFactory.getLogger(LoginTest.class);
	private WebDriver driver;
	TestBase testbase;
	LoginPage loginPage;
	DashboardPage dashboardPage;
	@Test(priority=1,description = "LoginTest_Successful", groups= {"sanity", "reg"})
  public void TC01_LoginTest_Successful() throws InterruptedException {
	  loginPage.loginToApplication("Admin", "admin123");
	  boolean actual = dashboardPage.isDashboardDisplayed();
	  testbase.attachScreenshotToTestNg(driver);
	  Assert.assertTrue(actual);
	  dashboardPage.logout();
  }
  
	@Test(priority=2,description = "LoginTest_UnSuccessful", groups= {"sanity", "reg"})
  public void TC02_LoginTest_UnSuccessful() {
	  loginPage.loginToApplication("Admin1", "admin123");
	  boolean actual = loginPage.isErrorDisplayed();
	  Assert.assertTrue(actual);
	  
  }
  
  @BeforeMethod(alwaysRun = true) //pre-condition for testcases in class
  public void beforeMethod() throws IOException {
	  testbase = new TestBase();
	  driver = testbase.getDriverInstance();
	  loginPage = new LoginPage(driver);
	  dashboardPage = new DashboardPage(driver);
  }

  @AfterMethod(alwaysRun = true) //post condition for testcases in class
  public void afterMethod() throws InterruptedException {
	  Assert.assertEquals(loginPage.isLoginPageDisplayed(), true);
	  testbase.attachScreenshotToTestNg(driver);
	 testbase.closeDriverInstance();
  }

  
}
