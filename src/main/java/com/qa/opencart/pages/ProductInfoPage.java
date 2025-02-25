package com.qa.opencart.pages;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	private Map<String,String> productMap;
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);

	}
	
	private By productHeader = By.tagName("h1");
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	private By quantity = By.id("input-quantity");
	private By addToCart = By.id("button-cart");
	private By successMsg = By.className("alert-success");
	private By shoppingCartLink = By.linkText("shopping cart");
	
	public String getProductHeader() {
		String header = eleUtil.doElementGetText(productHeader);
		//String header = driver.findElement(productHeader).getText();
		System.out.println("product header: " +header);
		return header;
	}
	
	public int getProductImagesCount() {
		int imagesCount = eleUtil.waitForElementsPresence(productImages, AppConstants.SHORT_TIME_OUT).size();
		System.out.println(getProductHeader() + ": Images Count = " +imagesCount);
		return imagesCount;
	}
	
	public void getProductMetaData() {
		List<WebElement> metaList = eleUtil.waitForElementsPresence(productMetaData, AppConstants.DEFAULT_TIME_OUT);
		for(WebElement e : metaList) {
			String metaText = e.getText();
			String meta[] = metaText.split(":");
			String metaKey = meta[0].trim();
			String metaValue = meta[1].trim();
			productMap.put(metaKey, metaValue);	
		}
	}
	
	public void getProductPriceData() {
		List<WebElement> priceList = eleUtil.waitForElementsPresence(productPriceData, AppConstants.DEFAULT_TIME_OUT);
		String productPrice = priceList.get(0).getText().trim();
		String productExTax = priceList.get(1).getText().split(":")[1].trim();
		productMap.put("price", productPrice);
		productMap.put("exTax", productExTax);
	}
	
	public Map<String,String> getProductInfo() {
		productMap = new LinkedHashMap<String,String>();
		productMap.put("header", getProductHeader());
		productMap.put("imagesCount", getProductImagesCount()+"");
		getProductMetaData();
		getProductPriceData();
		return productMap;
	}
	
	public ShoppingCartPage addProductToCart() {
		WebElement quantityEle = eleUtil.waitForElementVisible(quantity, AppConstants.DEFAULT_TIME_OUT);
		eleUtil.doSendKeys(quantityEle, "1");
		eleUtil.doClick(addToCart);
		eleUtil.waitForElementPresence(successMsg, AppConstants.DEFAULT_TIME_OUT);
		eleUtil.doClick(shoppingCartLink);
		return new ShoppingCartPage(driver);
	}
	
	
}
