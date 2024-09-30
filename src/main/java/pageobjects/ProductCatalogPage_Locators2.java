package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import abstractComponents.AbstractComponent;

public class ProductCatalogPage_Locators2 extends AbstractComponent {

	
   

	WebDriver driver;
	
	public ProductCatalogPage_Locators2(WebDriver driver) 
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	@FindBy(xpath ="//div[@class='card']/div")
	List<WebElement> eleproducts;
	
	
	By eleaddtocart=By.xpath("button[2]") ;
	
	By invisibilityOfElement= By.cssSelector("#toast-container");
	
	@FindBy(xpath ="//button[@routerlink='/dashboard/cart']")
	WebElement elecart;
	
	public List<WebElement> getProducts()
	{
		return eleproducts;
	}
	
	public void addItemsToCart(String[] names)
	{
		for(String name:names)
		{
			getProducts().stream().filter(s->s.getText().contains(name))
			.forEach(s->s.findElement(eleaddtocart).click());
			invisibilityOfElement(invisibilityOfElement);
			
		}
	}
	
	public void addItemsToCartsingle(String names)
	{
		
			getProducts().stream().filter(s->s.getText().contains(names))
			.forEach(s->s.findElement(eleaddtocart).click());
			invisibilityOfElement(invisibilityOfElement);
			
		
	}
	
	public CartPage_Locators gotoCart()
	{
		elecart.click();
		return new CartPage_Locators(driver);
	}
	
	
	

	
	
	
}
