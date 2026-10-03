package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdminPage {
	private static final Logger logger = LoggerFactory.getLogger(AdminPage.class);
	private WebDriver driver;
	public AdminPage(WebDriver dr)
	{
		this.driver=dr;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[@class='oxd-table-card']")
	private List<WebElement> rows;
	
	public String getAdminName(String str)
	{
		String name=null;
		try {
		for(int i=1;i<=rows.size();i++)//rows
		{
			int col = driver.findElements(By.xpath("//*[@class='oxd-table-card']["+i+"]/descendant::div[@class='oxd-table-cell oxd-padding-cell']/div")).size();
	
				WebElement userRole = driver.findElement(By.xpath("//*[@class='oxd-table-card']["+i+"]/descendant::div[@class='oxd-table-cell oxd-padding-cell'][3]/div"));
				logger.info("User Role found:"+ userRole.getText());
				WebElement userName = driver.findElement(By.xpath("//*[@class='oxd-table-card']["+i+"]/descendant::div[@class='oxd-table-cell oxd-padding-cell'][4]/div"));
				logger.info("User name found:"+ userName.getText());
			    if(userRole.getText().trim().equalsIgnoreCase("Admin") && 
			    		userName.getText().trim().equalsIgnoreCase(str))
					{
						name = userName.getText().trim();
						break;
					}
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		logger.info("Name found for admin:"+name);
		return name;
	}

}
