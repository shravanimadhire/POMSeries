package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
	private By footer = By.xpath("//footer//a");
	private By myAccountDropdown = By.xpath("//a[@title=\"My Account\"]");
	private By myAccountOption = By.xpath("//li[@class=\"dropdown open\"]//a[text()='My Account']");
	
	public boolean isLogoDisplayed() {
		return eleUtil.doIsElementDisplayed(logo);
	}
	
	public List<String> getFooterList() {
		List<WebElement> footerList = eleUtil.waitForElementsPresence(footer, AppConstants.DEFAULT_TIME_OUT);
		System.out.println("Total number of footers: " +footerList.size());
		List<String> footers = new ArrayList<String>();
		for(WebElement e : footerList){
			String text = e.getText();
			footers.add(text);
		}
		return footers;
	}
	
	public boolean checkFooterLink(String footerLink) {
		return getFooterList().contains(footerLink);	
	}
	
	
	public HomePage selectAccountOption(){
		eleUtil.waitForElementVisible(myAccountDropdown, AppConstants.SHORT_TIME_OUT).click();;
		eleUtil.waitForElementVisible(myAccountOption, AppConstants.SHORT_TIME_OUT).click();;
		//driver.findElement(myAccountDropdown).click();
		//driver.findElement(myAccountOption).click();
		return new HomePage(driver);
	}
	
	
}
