package testcases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.DirectoryPage;
import pages.LoginPage;
import testbase.Base;
import testbase.TestBase;

public class DirectoryTest extends Base {
    private WebDriver driver;
    private LoginPage loginPage;
    private DirectoryPage directoryPage;
    private TestBase testbase;

    @BeforeClass
    public void setUp() throws IOException {
    	testbase = new TestBase();
        driver = testbase.getDriverInstance();;
        loginPage = new LoginPage(driver);
        directoryPage = new DirectoryPage(driver);
        loginPage.loginToApplication("admin", "admin123");
    }

    @Test(priority = 1)
    public void testViewDirectoryList() {
        directoryPage.navigateToDirectory();
        Assert.assertTrue(directoryPage.isDirectoryListDisplayed(), "Directory list should be displayed");
    }

    @Test(priority = 2)
    public void testSearchDirectory() throws InterruptedException {
        directoryPage.navigateToDirectory();
        directoryPage.searchDirectory("Ranga");
        Assert.assertTrue(directoryPage.isSearchResultDisplayed("Ranga  Akunuri"), "Search result should display 'Ranga  Akunuri'");
    }

    @Test(priority = 3)
    public void testUnauthorizedAccess() {
        directoryPage.logout();
        directoryPage.navigateToDirectoryDetails();
        Assert.assertTrue(directoryPage.isAccessDeniedMessageDisplayed(), "Access denied message should be displayed");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}