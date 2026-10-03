package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import testbase.Base;

import java.time.Duration;

public class DirectoryPage extends Base {
    private WebDriver driver;
    private WebDriverWait wait;

    public DirectoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[text()='Directory']")
    private WebElement directoryMenu;

    @FindBy(css = "div.orangehrm-directory-card")
    private WebElement directoryList;

    @FindBy(css = "input[placeholder='Type for hints...']")
    private WebElement searchInput;

    @FindBy(css = "button[type='submit']")
    private WebElement searchButton;

    @FindBy(css = "div.orangehrm-directory-result")
    private WebElement searchResult;

    @FindBy(css = "div.orangehrm-access-denied")
    private WebElement accessDeniedMessage;

    @FindBy(css = "button[aria-label='Logout']")
    private WebElement logoutButton;

    @FindBy(css = "div.oxd-autocomplete-dropdown")
    private WebElement searchDropdown;
    
    public void navigateToDirectory() {
        wait.until(ExpectedConditions.elementToBeClickable(directoryMenu)).click();
    }

    public boolean isDirectoryListDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(directoryList)).isDisplayed();
    }

    public void searchDirectory(String directoryName) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(searchInput)).clear();
        //searchInput.sendKeys(directoryName);
        //waitForElementVisible(driver, searchDropdown);
        new Actions(driver).sendKeys(searchInput, directoryName).pause(Duration.ofSeconds(4)).perform();
        new Actions(driver).moveToElement(searchDropdown).click().perform();

        searchButton.click();
    }

    public boolean isSearchResultDisplayed(String expectedName) {
        return wait.until(ExpectedConditions.textToBePresentInElement(searchResult, expectedName));
    }

    public void navigateToDirectoryDetails() {
        // Assuming clicking on first directory in list to view details
        wait.until(ExpectedConditions.elementToBeClickable(directoryList)).click();
    }

    public boolean isAccessDeniedMessageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(accessDeniedMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void logout() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }
}