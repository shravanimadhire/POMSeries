package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.constants.AppError;

public class ProductInfoPageTest extends BaseTest{

	
	@BeforeClass
	public void productInfoSetup() {
		homepage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void productSearchHeaderTest() {
		searchResultsPage = homepage.doSearch("macbook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		String actualProductHeader = productInfoPage.getProductHeader();
		Assert.assertEquals(actualProductHeader, "MacBook Pro");
	}
	
	@DataProvider
	public Object[][] getProductImageData(){
		return new Object[][] {
			{"macbook", "MacBook Pro", 4},
			{"macbook", "MacBook Air", 4},
			{"imac", "iMac", 3},
			{"samsung", "Samsung SyncMaster 941BW", 1},
			{"samsung", "Samsung Galaxy Tab 10.1", 7},
		};
	}
	
	
	@Test(dataProvider = "getProductImageData")
	public void productImagesCountTest(String searchKey, String productName, int expectedImagesCount) {
		searchResultsPage = homepage.doSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(productName);
		int actualProductImagesCount = productInfoPage.getProductImagesCount();
		Assert.assertEquals(actualProductImagesCount, expectedImagesCount);		
	}
	
	@Test
	public void productInfoTest() {
		searchResultsPage = homepage.doSearch("macbook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		Map<String,String> productInfoMap = productInfoPage.getProductInfo();
		productInfoMap.forEach((k,v)->System.out.println(k+":"+v));
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(productInfoMap.get("header"), "MacBook Pro");
		
		softAssert.assertEquals(productInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(productInfoMap.get("Availability"), "In Stock");
		softAssert.assertEquals(productInfoMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(productInfoMap.get("Reward Points"), "800");
		
		softAssert.assertEquals(productInfoMap.get("price"), "$2,000.00");
		softAssert.assertEquals(productInfoMap.get("exTax"), "$2,000.00");

		softAssert.assertAll();
	}
	
	@Test
	public void shoppingCartTest() {
		searchResultsPage = homepage.doSearch("macbook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		cartPage = productInfoPage.addProductToCart();
		Assert.assertTrue(cartPage.verifyProduct("MacBook Pro"), AppError.PRODUCT_NOT_FOUND_ERROR);
	}
	
}
