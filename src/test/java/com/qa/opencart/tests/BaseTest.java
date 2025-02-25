package com.qa.opencart.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.CommonsPage;
import com.qa.opencart.pages.HomePage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.LogoutPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.SearchResultsPage;
import com.qa.opencart.pages.ShoppingCartPage;

public class BaseTest {

	WebDriver driver;
	DriverFactory df;
	
	protected Properties prop;
	
	protected LoginPage loginpage;
	protected HomePage homepage;
	protected SearchResultsPage searchResultsPage;
	protected ProductInfoPage productInfoPage;
	protected CommonsPage commonsPage;
	protected LogoutPage logoutPage;
	protected ShoppingCartPage cartPage;

	@BeforeTest
	public void setup() {
		df = new DriverFactory();
		prop = df.initProp();
		driver = df.initDriver(prop);
		loginpage = new LoginPage(driver);
		commonsPage = new CommonsPage(driver);
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	
}
