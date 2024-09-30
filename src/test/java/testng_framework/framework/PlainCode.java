package testng_framework.framework;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PlainCode{

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		
		//signin it the page
		driver.findElement(By.id("userEmail")).sendKeys("anshika@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Iamking@000");
		driver.findElement(By.name("login")).click();
		
		//add item to the cart
		String[] names= {"ZARA COAT 3","ADIDAS ORIGINAL"};
		List<WebElement> products = driver.findElements(By.xpath("//div[@class='card']/div"));
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
		for(String name:names)
		{
			products.stream().filter(s->s.getText().contains(name))
			.forEach(s->s.findElement(By.xpath("button[2]")).click());
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#toast-container")));
		}
		
		//click on cart
		Thread.sleep(5000);
		
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		
		//verify products in cart
		List<WebElement> productsincart = driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
		
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
		
		// click on checkout 
		
		WebElement checkout=driver.findElement(By.cssSelector("div[class='subtotal cf ng-star-inserted'] ul li:nth-child(3) button"));	
		
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", checkout);
		
		//placing order
		
		driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("ind");
		List<WebElement> countries=driver.findElements(By.cssSelector("button[class='ta-item list-group-item ng-star-inserted']"));
		try {
		countries.stream().filter(s->s.getText().equalsIgnoreCase("India")).forEach(s->s.click());
		}
		catch (StaleElementReferenceException e) {
			
		}
		driver.findElement(By.xpath("//a[text()='Place Order ']")).click();
		
		//get order id
		List<WebElement> orderIDs= driver.findElements(By.xpath("//td[@class='em-spacer-1']/label[@class='ng-star-inserted']"));
		
		List<String> IDs=orderIDs.stream().map(s->s.getText()).toList();
		for(String ID:IDs)
		{
			System.out.println(ID.split(" ")[1].trim());
		}
		String confirm=driver.findElement(By.className("hero-primary")).getText();
		Assert.assertTrue(confirm.equalsIgnoreCase("Thankyou for the order."));
		
		driver.quit();
	}

}
