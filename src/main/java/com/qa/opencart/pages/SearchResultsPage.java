package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class SearchResultsPage {

	WebDriver driver;
	private ElementUtil eleUtil;

	
	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	private By productResults = By.cssSelector("div.product-thumb");
	
	
	public int getProductResultsCount() {
		return eleUtil.waitForElementsPresence(productResults, AppConstants.DEFAULT_TIME_OUT).size();
		//return driver.findElements(productResults).size();
	}
	
	public ProductInfoPage selectProduct(String productName) {
		System.out.println("product name: " +productName );
		eleUtil.doClick(By.linkText(productName));
		//driver.findElement(By.linkText(productName)).click();
		return new ProductInfoPage(driver);
	}
}
