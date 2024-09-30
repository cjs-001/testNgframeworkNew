package page_object_model;

import java.io.IOException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobjects.CartPage_Locators;
import pageobjects.CheckOutPage_Locators;
import pageobjects.ConfirmationPage_locators;
import pageobjects.OrderPage_locators;
import pageobjects.ProductCatalogPage_Locators2;
import testcomponents.BaseTest;

public class ErrorValidation extends BaseTest {

	
	
	String[] names= {"ZARA COAT 3","ADIDAS ORIGINAL"};
	String[] fakenames= {"puma","luma"};
	
	@Test
	public void loginError() throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		loginpage.logintoapplication("annshika@gmail.com", "Iaamking@000");
		Assert.assertEquals("Incorrect email or password.", loginpage.errorForLoginPage());
	}
	
	@Test
	public void productError() throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		
		
		ProductCatalogPage_Locators2 productpage=  loginpage.logintoapplication("anshika@gmail.com", "Iamking@000");
		productpage.addItemsToCart(names);
		Thread.sleep(5000);
		
		CartPage_Locators cartpage= productpage.gotoCart();
		cartpage.verifyCartProducts(fakenames);
		
		CheckOutPage_Locators checkout = cartpage.checkout();
		checkout.selectCountry("India");
		
		ConfirmationPage_locators confirmpage =checkout.placeOrder();
		confirmpage.verify();
	}
	
	@Test(dataProvider = "getData",groups = "hashmapdata")
	public void loginErrordataset(HashMap<String, String> datas) throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		loginpage.logintoapplication(datas.get("email"), datas.get("pass"));
		Assert.assertEquals("Incorrect email or password.", loginpage.errorForLoginPage());
	}
	
	@DataProvider
	public Object[][] getData()
	{
		HashMap<String, String> data1 = new HashMap<String, String>();
		data1.put("email", "anshika@gmail.com");
		data1.put("pass", "12345");
		HashMap<String, String> data2 = new HashMap<String, String>();
		data2.put("email", "anshika@gmail.com");
		data2.put("pass", "Iamking@0002");
		HashMap<String, String> data3 = new HashMap<String, String>();
		data3.put("email", "Iamking@000");
		data3.put("pass", "anshika@gmail.com");
		
		Object[][] login= new Object[][] {{data1},{data2},{data3}};
		
		return login;
	}
	
	

}
