package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class CommonsPage {

private WebDriver driver;
private ElementUtil eleUtil;

	
	public CommonsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);

	}
	
	private By logo = By.className("img-responsive");
	private By myAccountDropdown = By.xpath("//a[@title=\"My Account\"]");
	private By myAccountOption = By.xpath("//li[@class=\"dropdown open\"]//a[text()='My Account']");
	
	
	public HomePage selectAccountOption(){
		eleUtil.waitForElementVisible(myAccountDropdown, AppConstants.SHORT_TIME_OUT).click();;
		eleUtil.waitForElementVisible(myAccountOption, AppConstants.SHORT_TIME_OUT).click();;
		//driver.findElement(myAccountDropdown).click();
		//driver.findElement(myAccountOption).click();
		return new HomePage(driver);
	}
	
	
}
