package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import testbase.Base;

public class LoginPage extends Base{
	private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);
	private WebDriver driver;
	//constructor
	public LoginPage(WebDriver dr)
	{
		this.driver=dr;
		PageFactory.initElements(driver, this);
	}
	//Locators
	@FindBy(name="username")
	private WebElement user;
	
	@FindBy(name="password")
	private WebElement pass;
	
	@FindBy(css="[type='submit']")
	private WebElement loginButton;
	
	@FindBy(css="[class$='oxd-alert-content-text']")
	private WebElement error;
	//Functions on the page
	
	
	public void loginToApplication(String username, String password)
	{
		logger.info("Login to application: user:"+username+" & "+password);
		user.sendKeys(username);
		pass.sendKeys(password);
		loginButton.click();
		
	}
	
	public boolean isLoginPageDisplayed()
	{
		boolean flag =false;
		try {
			flag = loginButton.isDisplayed();
		}catch(Exception e)
		{
			e.fillInStackTrace();
		}
		logger.info("Login Page is displayed:"+flag);
		return flag;
	}
	
	public boolean isErrorDisplayed()
	{
		boolean flag =false;
		try {
			flag = error.isDisplayed();
		}catch(Exception e)
		{
			e.fillInStackTrace();
		}
		logger.info("error is displayed:"+flag);
		return flag;
	}
}
