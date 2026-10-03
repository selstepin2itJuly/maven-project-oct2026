package testcases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.DashboardPage;
import pages.LoginPage;
import pages.MyInfoPage;
import testbase.TestBase;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class MyInfoTest {
	private static final Logger logger = LoggerFactory.getLogger(MyInfoPage.class);
	WebDriver driver;
	TestBase testbase;
	LoginPage loginpage;
	DashboardPage dashboardpage;
	MyInfoPage myinfopage;
	
  @Test(priority=1,description = "Verify_The_Menu_Item_Count_On_MyInfo",groups= {"sanity"})
  public void TC05_Verify_The_Menu_Item_Count_On_MyInfo() {
	  int actual = myinfopage.getMenuItemCount();
	  Assert.assertEquals(actual, 10);
  }
  
  @Test(priority=0, groups= {"sanity"},description = "Verify_The_Menu_Item_Text_On_MyInfo", dependsOnMethods = "TC05_Verify_The_Menu_Item_Count_On_MyInfo")
  public void TC06_Verify_The_Menu_Item_Text_On_MyInfo() throws InterruptedException {
	  List<String> actual = myinfopage.getMenuItemTexts();
	  testbase.attachScreenshotToTestNg(driver);
	  List<String> expected = new ArrayList<String>();
	  expected.add("Personal Details");
	  expected.add("Contact Details");
	  expected.add("Emergency Contacts");
	  expected.add("Dependents");
	  expected.add("Immigration");
	  expected.add("Jobs");
	  expected.add("Salary");
	  //expected.add("Tax Exemptions");
	  expected.add("Report-to-");
	  expected.add("Qualifications");
	  expected.add("Memberships");
	  SoftAssert sa = new SoftAssert();
	  int i=0;
	  for(String s:expected)
	  {
		  sa.assertEquals(actual.get(i), s);
		  i++;
	  }
	 sa.assertAll();
  }
  @BeforeClass(alwaysRun = true)
  public void beforeMethod() throws IOException {
	  logger.info("Before Method: Initialization of Browser");
	  testbase = new TestBase();
	  driver = testbase.getDriverInstance();
	  loginpage = new LoginPage(driver);
	  dashboardpage = new DashboardPage(driver);
	  loginpage.loginToApplication("Admin", "admin123");
	  assertEquals(dashboardpage.isDashboardDisplayed(), true);
	  logger.info("Before Method: Click On info");
	  myinfopage=dashboardpage.clickOnMyInfo();
  }

  @AfterClass(alwaysRun = true)
  public void afterMethod() throws InterruptedException {
	  dashboardpage.logout();
	  Assert.assertEquals(loginpage.isLoginPageDisplayed(), true);
	  testbase.attachScreenshotToTestNg(driver);
	  testbase.closeDriverInstance();
  }

}
