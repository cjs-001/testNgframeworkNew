package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import abstractComponents.AbstractComponent;

public class ConfirmationPage_locators extends AbstractComponent{

	 WebDriver driver;
		
		public ConfirmationPage_locators(WebDriver driver) 
		{
			super(driver);
			this.driver=driver;
			PageFactory.initElements(driver, this);
		}
		

		
		@FindBy(css ="//td[@class='em-spacer-1']/label[@class='ng-star-inserted']")
		List<WebElement> orderIDs;
		
		@FindBy(className="hero-primary")
		WebElement eleconfirmmsg;
		
		public List<String> getOrderIDs()
		{
			List<String> IDs=orderIDs.stream().map(s->s.getText().split(" ")[1].trim()).toList();
			return IDs;
		}
		
		public OrderPage_locators verify()
		{
            String confirm=eleconfirmmsg.getText();
			Assert.assertTrue(confirm.equalsIgnoreCase("Thankyou for the order."));
			return new OrderPage_locators(driver);
		}
}
