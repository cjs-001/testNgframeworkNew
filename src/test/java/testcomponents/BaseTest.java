package testcomponents;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import pageobjects.LoginPage_Locators;

public class BaseTest {

	public LoginPage_Locators loginpage;
	public WebDriver driver;
	
	
	
	
	public WebDriver initializeDriver() throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\globalproperties\\GlobalData.properties");
		prop.load(fis);
		
		String BrowserName = System.getProperty("browser")!=null ? System.getProperty("browser"): prop.getProperty("browser");
		
		
	
		
		if(BrowserName.contains("chrome"))
		{
		   
		    if(BrowserName.contains("headless"))
			{
				ChromeOptions options = new ChromeOptions();
				options.addArguments("headless");
				driver = new ChromeDriver(options);
				driver.manage().window().fullscreen();
			}else  driver = new ChromeDriver();
		}
		else if(BrowserName.contains("edge"))
		{
			//driver = new EdgeDriver();
			 if(BrowserName.contains("headless"))
				{
					EdgeOptions options = new EdgeOptions();
					options.addArguments("headless");
					driver = new EdgeDriver(options);
					driver.manage().window().fullscreen();
				}
		}
		
		
		
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
		driver.manage().window().maximize();
		return driver;
	}
	
	@BeforeMethod(alwaysRun = true)
	public LoginPage_Locators login() throws IOException
	{
		driver=initializeDriver();
		loginpage=new LoginPage_Locators(driver);
		loginpage.launchURL();
		return loginpage;
	}
	
	@AfterMethod(alwaysRun = true)
	public void close()
	{
		driver.quit();
	}
	
	public String takeSS(String testcasename, WebDriver driver) throws IOException
	{
        File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		FileUtils.copyFile(src, new File(System.getProperty("user.dir")+"//reports//"+testcasename+".png"));
		
		return System.getProperty("user.dir")+"//reports//"+testcasename+".png";
		
	}
}

