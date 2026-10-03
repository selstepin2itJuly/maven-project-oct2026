package testbase;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Reporter;

public class Base {

	private static final Logger logger = LoggerFactory.getLogger(Base.class);
	public void scrollToElementJS(WebDriver driver, WebElement ele)
	{
		logger.info("scroll to element JS");
		JavascriptExecutor j = (JavascriptExecutor)driver;
		j.executeScript("arguments[0].scrollIntoView(false);",ele);
		j.executeScript("window.scrollBy(0,400)", "");
	}
	
	public void scrollToElementActions(WebDriver driver, WebElement ele)
	{
		logger.info("scroll to element Actions");
		Actions ac = new Actions(driver);
		ac.scrollToElement(ele).perform();
		((JavascriptExecutor)driver).executeScript("window.scrollBy(0,400)", "");
	}

	public void waitForElement(WebDriver driver, WebElement ele)
	{
		logger.info("Wait for Element->\""+ele+"\" for 15 sec");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}
	
	public void attachScreenshotToTestNg(WebDriver driver) throws InterruptedException {
		Thread.sleep(3000);
		TakesScreenshot tc = (TakesScreenshot) driver;
		String src = tc.getScreenshotAs(OutputType.BASE64);
		// Embed screenshot in report
		String htmlImage = "<img src=\"data:image/png;base64, " + src + "\" height=\"550\" width=\"700\" />";
		Reporter.log(htmlImage);
	}

}
