package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class LoginPage_Locators extends AbstractComponent{

	WebDriver driver;
	
	
	public LoginPage_Locators(WebDriver driver) 
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id ="userEmail")
	WebElement eleusername;
	
	@FindBy(id ="userPassword")
	WebElement elepassword;
	
	@FindBy(id ="login")
	WebElement login;
	
	@FindBy(css ="[class*='flyInOut']")
	WebElement eleerror;
	
	
	
	public pageobjects.ProductCatalogPage_Locators2 logintoapplication(String username,String password)
	{
		eleusername.sendKeys(username);
		elepassword.sendKeys(password);
		login.click();
		return new ProductCatalogPage_Locators2(driver);
	}
	
	public void launchURL()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String errorForLoginPage()
	{
		visibilityOfElement(eleerror);
		return eleerror.getText();
	}
	
	
}
