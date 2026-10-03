package testcases;

import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.LoginPage;
import testbase.TestBase;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class LoginTestDataProvider {
	private static final Logger logger = LoggerFactory.getLogger(LoginTestDataProvider.class);
	private WebDriver driver;
	TestBase testbase;
	LoginPage loginPage;
	DashboardPage dashboardPage;
	
	@Test(dataProvider = "test-data",priority=1,description = "LoginTest_Successful", groups= {"sanity", "reg"})
  public void TC01_LoginTest(String user, String pass) throws InterruptedException {
	  loginPage.loginToApplication(user, pass);
	  logger.info("Username:"+user+"  Password:"+pass);
	  boolean actual = dashboardPage.isDashboardDisplayed();
	  testbase.attachScreenshotToTestNg(driver);
	  Assert.assertTrue(actual);
	  dashboardPage.logout();
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

  @Test(dataProvider = "test-data")
  public void sampleTestMethod(int input) {
      System.err.println("Input value = " + input);
  }

  @DataProvider(name = "test-data")
  public String[][] testDataSupplier() {
      return new String[][]{
              {"Admin", "admin123"}, 
              {"Admin1", "admin123"},
              {"Admin", "admin1234"},
              {"Admin1", "admin1234"}
      };
  }
}
