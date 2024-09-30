package pageobjects;



import static org.testng.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import abstractComponents.AbstractComponent;

public class CartPage_Locators extends AbstractComponent{

	CheckOutPage_Locators checkOutPage_Locators;
    WebDriver driver;
	
	public CartPage_Locators(WebDriver driver) 
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath ="//div[@class='cartSection']/h3")
	List<WebElement> productsincart;
	
	@FindBy(xpath ="//div[@class='subtotal cf ng-star-inserted']/ul/li[3]/button")
	WebElement elecheckout;
	
	
	public void verifyCartProducts(String[] names)
	{
		for(int i=1;i<productsincart.size();i++)
		{
			String cartproducts = productsincart.get(i).getText();
			List<String> namelist=Arrays.asList(names);
			if(namelist.contains(cartproducts))
			{
				Assert.assertTrue(true);
			}
			else assertTrue(false);
			
		}
	}
	
	public void verifyCartProductsSingle(String names)
	{
		for(int i=1;i<productsincart.size();i++)
		{
			String cartproducts = productsincart.get(i).getText();
			
			if(cartproducts.contains(names))
			{
				Assert.assertTrue(true);
			}
			else assertTrue(false);
			
		}
	}
	
	public CheckOutPage_Locators checkout()
	{
		jsexecutorclick(elecheckout);
		return new CheckOutPage_Locators(driver);
	}
}
