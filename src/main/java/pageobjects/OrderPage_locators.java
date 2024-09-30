package pageobjects;

import static org.testng.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import abstractComponents.AbstractComponent;

public class OrderPage_locators extends AbstractComponent {

	WebDriver driver;
	public OrderPage_locators(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath ="//tr[@class='ng-star-inserted']/td[2]")
	List<WebElement> eleorders;
	
	public void verifyOrders(String[] names)
	{
		ordersPage();
		for(int i=1;i<eleorders.size();i++)
		{
			String orderedproducts = eleorders.get(i).getText();
			List<String> namelist=Arrays.asList(names);
			if(namelist.contains(orderedproducts))
			{
				Assert.assertTrue(true);
			}
			else assertTrue(false);
		}
		
	}

	
	
}
