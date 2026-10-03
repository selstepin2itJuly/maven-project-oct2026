# Step 5: Java + Selenium + TestNG Automation

```java
package testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.DirectoryPage;
import pages.LoginPage;
import testbase.Base;

public class DirectoryTest extends Base {
    private WebDriver driver;
    private LoginPage loginPage;
    private DirectoryPage directoryPage;

    @BeforeClass
    public void setUp() {
        driver = initializeDriver();
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
    public void testSearchDirectory() {
        directoryPage.searchDirectory("Sales");
        Assert.assertTrue(directoryPage.isSearchResultDisplayed("Sales"), "Search result should display 'Sales'");
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
```
