package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import testbase.Base;

public class DashboardPage extends Base{
	private static final Logger logger = LoggerFactory.getLogger(DashboardPage.class);
	private WebDriver driver;
	public DashboardPage(WebDriver dr)
	{
		this.driver=dr;
		PageFactory.initElements(driver, this);
	}
	
	//Locator
	@FindBy(css="h6[class$='oxd-topbar-header-breadcrumb-module']")
	private WebElement dashboard;
	
	@FindBy(css="[class='oxd-userdropdown-name']")
	private WebElement logoutDropdown;
	
	@FindBy(xpath="//a[@class='oxd-userdropdown-link'][text()='Logout']")
	private WebElement logout;
	
	@FindBy(xpath="//span[contains(@class,'oxd-text--span oxd-main-menu-item--name')][text()='My Info']")
	private WebElement myInfo;
	
	@FindBy(xpath="//*[contains(@class,'oxd-main-menu-item--name')][text()='Admin']")
	private WebElement admin;
	
	public boolean isDashboardDisplayed()
	{
		boolean flag =false;
		try {
			waitForElement(driver, dashboard);
			flag = dashboard.isDisplayed();
		}catch(Exception e)
		{
			e.fillInStackTrace();
		}
		logger.info("Dashboard Page is displayed:"+flag);
		return flag;
	}
	
	public MyInfoPage clickOnMyInfo()
	{
		myInfo.click();
		logger.info("Click on My Infor:"+myInfo);
		return new MyInfoPage(driver);
	}
	
	public AdminPage clickOnAdmin()
	{
		admin.click();
		logger.info("click On Admin:"+admin);
		return new AdminPage(driver);
	}
	public void logout()
	{
		logger.info("Logout");
		logoutDropdown.click();
		logout.click();
	}
}
