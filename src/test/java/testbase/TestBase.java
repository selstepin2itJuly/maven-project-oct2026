package testbase;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestBase extends Base {
	private static final Logger logger = LoggerFactory.getLogger(TestBase.class);
	private WebDriver driver;
	private String browser;
	private Properties prop;
	
	public WebDriver getDriverInstance() throws IOException
	{
	logger.info("Initialization of properties file for browser and url");
	 String config = "./src/test/resources/config/config.properties";
	 FileInputStream inStream=new FileInputStream(config);
	 prop = new Properties();
	 prop.load(inStream);
	 browser = prop.getProperty("browser");
	 logger.info("Browser selected->"+browser);
	 if(browser.equalsIgnoreCase("chrome")) {
		driver = new ChromeDriver();
	 }
	 else if(browser.equalsIgnoreCase("firefox"))
	 {
		 driver = new FirefoxDriver();
	 }else if(browser.equalsIgnoreCase("edge"))
	 {
		 driver = new EdgeDriver();
	 }else
	 {
		 Throwable thr = new Throwable();
		 thr.initCause(null);
	 }
	 driver.manage().window().maximize();
	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	 logger.info("Test Url->"+prop.getProperty("url"));
	 driver.get(prop.getProperty("url"));
	 return driver;
	}
	
	public void closeDriverInstance()
	{
		logger.info("Quit Browser");
		driver.quit();
	}
}
