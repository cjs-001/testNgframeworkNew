package abstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageobjects.OrderPage_locators;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AbstractComponent {

	WebDriver driver;
	public AbstractComponent(WebDriver driver) 
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="button[routerlink='/dashboard/myorders']")
	WebElement eleorder;
	
	public void invisibilityOfElement(By locator)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}
	
	public void visibilityOfElement(WebElement webelement)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
		wait.until(ExpectedConditions.visibilityOf(webelement));
	}
	
	public void jsexecutorclick(WebElement element)
	{
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
	}
	
	public OrderPage_locators ordersPage()
	{
		eleorder.click();
		return new OrderPage_locators(driver);
	}
}
