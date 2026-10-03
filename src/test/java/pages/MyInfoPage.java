package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyInfoPage {
	private static final Logger logger = LoggerFactory.getLogger(MyInfoPage.class);
	private WebDriver driver;
	public MyInfoPage(WebDriver dr)
	{
		this.driver=dr;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[contains(@class,'orangehrm-tabs-item')]")
	private List<WebElement> menuItem;
	
	public int getMenuItemCount()
	{
		logger.info("count of items:"+menuItem.size());
		return menuItem.size();
	}
	
	public List<String> getMenuItemTexts()
	{
		List<String> temp = new ArrayList<String>();
		for(WebElement e:menuItem)
		{
			temp.add(e.getText());
		}
		logger.info("List of Elements:"+temp);
		return temp;
	}
}
