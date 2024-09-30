package page_object_model;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import data.DataReader;
import data.DataReader2;
import pageobjects.CartPage_Locators;
import pageobjects.CheckOutPage_Locators;
import pageobjects.ConfirmationPage_locators;
import pageobjects.LoginPage_Locators;
import pageobjects.OrderPage_locators;
import pageobjects.ProductCatalogPage_Locators2;
import testcomponents.BaseTest;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StandAlone extends BaseTest {

	
	String[] names= {"ZARA COAT 3","ADIDAS ORIGINAL"};
	@Test(groups = {"retrytest"} ,retryAnalyzer =testcomponents.Retrytest.class)
	public void test() throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		ProductCatalogPage_Locators2 productpage=  loginpage.logintoapplication("anshika@gmail.com", "Iamking@000");
		productpage.addItemsToCart(names);
		Thread.sleep(5000);
		
		CartPage_Locators cartpage= productpage.gotoCart();
		cartpage.verifyCartProducts(names);
		
		CheckOutPage_Locators checkout = cartpage.checkout();
		checkout.selectCountry("India");
		
		ConfirmationPage_locators confirmpage =checkout.placeOrder();
		confirmpage.verify();
		
	}
	
	@Test(dependsOnMethods = {"test"})
	public void orderError() throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		ProductCatalogPage_Locators2 productpage=  loginpage.logintoapplication("anshika@gmail.com", "Iamking@000");
		OrderPage_locators orderpage=productpage.ordersPage();
		orderpage.verifyOrders(names);
	}
	
	@Test(dataProvider = "getData" , groups = "dataTesting")
	public void dataTest(String uname,String password,String product) throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		ProductCatalogPage_Locators2 productpage=  loginpage.logintoapplication(uname, password);
		productpage.addItemsToCartsingle(product);
		Thread.sleep(5000);
		
		CartPage_Locators cartpage= productpage.gotoCart();
		cartpage.verifyCartProductsSingle(product);
		
		CheckOutPage_Locators checkout = cartpage.checkout();
		checkout.selectCountry("India");
		
		ConfirmationPage_locators confirmpage =checkout.placeOrder();
		confirmpage.verify();
		
	}
	
	@DataProvider
	public Object[][] getData()
	{
		Object[][] login= new Object[2][3];
		login[0][0]="anshika@gmail.com";
		login[0][1]="Iamking@000";
		login[0][2]="ADIDAS ORIGINAL";
		login[1][0]="shetty@gmail.com";
		login[1][1]="Iamking@000";
		login[1][2]="ZARA COAT 3";
		return login;
	}
	
	@Test(dataProvider = "getDataJson" , groups = "dataTestingJson")
	public void dataTestJson(HashMap<String,String> datas) throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		ProductCatalogPage_Locators2 productpage=  loginpage.logintoapplication(datas.get("email"), datas.get("password"));
		productpage.addItemsToCartsingle(datas.get("product"));
		Thread.sleep(5000);
		
		CartPage_Locators cartpage= productpage.gotoCart();
		cartpage.verifyCartProductsSingle(datas.get("product"));
		
		CheckOutPage_Locators checkout = cartpage.checkout();
		checkout.selectCountry("India");
		
		ConfirmationPage_locators confirmpage =checkout.placeOrder();
		confirmpage.verify();
		
	}
	
	@DataProvider
	public Object[][] getDataJson() throws IOException
	{
		List<HashMap<String,String>> data=DataReader.getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\data\\data.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
		
	}
	
	@Test(dataProvider = "getDataJson2" , groups = "dataTestingJson2")
	public void dataTestJson2(HashMap<String,String> datas) throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		ProductCatalogPage_Locators2 productpage=  loginpage.logintoapplication(datas.get("email"), datas.get("password"));
		productpage.addItemsToCartsingle(datas.get("product"));
		Thread.sleep(5000);
		
		CartPage_Locators cartpage= productpage.gotoCart();
		cartpage.verifyCartProductsSingle(datas.get("product"));
		
		CheckOutPage_Locators checkout = cartpage.checkout();
		checkout.selectCountry("India");
		
		ConfirmationPage_locators confirmpage =checkout.placeOrder();
		confirmpage.verify();
		
	}
	
	@DataProvider
	public Object[][] getDataJson2() throws IOException, ParseException
	{
		List<HashMap<String,String>> data= DataReader2.datatoobject();
		return new Object[][] {{data.get(0)},{data.get(1)}};
		
	}

}
