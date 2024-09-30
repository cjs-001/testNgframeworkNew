package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class CheckOutPage_Locators extends AbstractComponent{


	
    WebDriver driver;
	
	public CheckOutPage_Locators(WebDriver driver) 
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="input[placeholder='Select Country']")
	WebElement elecountryname;
	
	@FindBy(css ="button[class='ta-item list-group-item ng-star-inserted']")
	List<WebElement> elecountries;
	
	@FindBy(xpath="//a[text()='Place Order ']")
	WebElement eleplaceorder;
	
	public void selectCountry(String country)
	{
		elecountryname.sendKeys(country);
		try {
		elecountries.stream().filter(s->s.getText().equalsIgnoreCase(country)).forEach(s->s.click());
		}
		catch (StaleElementReferenceException e) {
			
		}
	}
	
	public ConfirmationPage_locators placeOrder()
	{
		eleplaceorder.click();
		return new ConfirmationPage_locators(driver);
	}
}
