package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import testbase.Base;

public class PIMPage extends Base {
	private static final Logger logger = LoggerFactory.getLogger(PIMPage.class);
	private WebDriver driver;

	public PIMPage(WebDriver dr) {
		this.driver = dr;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@placeholder='Type for hints...']")
	private WebElement employeeNameInput;

	@FindBy(xpath = "//label[normalize-space()='Employment Status']/following::div[contains(@class,'oxd-select-wrapper')][1]")
	private WebElement employmentStatusDropdown;

	@FindBy(xpath = "//button[normalize-space()='Search']")
	private WebElement searchButton;

	@FindBy(xpath = "//*[@class='oxd-table-card']")
	private List<WebElement> resultRows;

	public void searchEmployeeByName(String employeeName) {
		logger.info("Searching for employee: " + employeeName);
		waitForElement(driver, employeeNameInput);
		employeeNameInput.clear();
		employeeNameInput.sendKeys(employeeName);
	}

	public void selectEmploymentStatus(String status) {
		logger.info("Selecting Employment Status: " + status);
		waitForElement(driver, employmentStatusDropdown);
		employmentStatusDropdown.click();
		WebElement statusOption = driver.findElement(
			By.xpath("//div[@role='option'][normalize-space()=\"" + status + "\"]")
		);
		waitForElement(driver, statusOption);
		statusOption.click();
	}

	public void clickSearchButton() {
		logger.info("Clicking Search button");
		waitForElement(driver, searchButton);
		searchButton.click();
	}

	public boolean isSearchResultsDisplayed() {
		boolean flag = false;
		try {
			Thread.sleep(2000);
			flag = !resultRows.isEmpty();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Search results displayed: " + flag);
		return flag;
	}

	public boolean isEmployeeDisplayedWithStatus(String employeeName, String status) {
		boolean flag = false;
		try {
			Thread.sleep(2000);
			List<WebElement> tableRows = driver.findElements(By.xpath("//*[@class='oxd-table-card']"));
			for (WebElement row : tableRows) {
				String rowText = row.getText();
				if (rowText.toLowerCase().contains(employeeName.toLowerCase())
						&& rowText.toLowerCase().contains(status.toLowerCase())) {
					flag = true;
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Employee + status match found: " + flag);
		return flag;
	}
}
