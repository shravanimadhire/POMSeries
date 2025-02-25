package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class HomePage {

private WebDriver driver;
private ElementUtil eleUtil;

	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);

	}
	
	private By logoutLink = By.linkText("Logout");
	private By headers = By.cssSelector("div#content h2") ;
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("div#search button");
	
	public String getHomePageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstants.HOME_PAGE_TITLE, AppConstants.DEFAULT_TIME_OUT);
		//String title = driver.getTitle();
		System.out.println("home page title ==> " +title);
		return title;
	}
	
	public String getHomePageURL() {
		String url = eleUtil.waitForURLContains(AppConstants.HOME_PAGE_URL_FRACTION, AppConstants.DEFAULT_TIME_OUT);
		//String url = driver.getCurrentUrl();
		System.out.println("home page url ===>" +url);
		return url;
	}
	

	public boolean isLogoutLinkExist() {
		return eleUtil.doIsElementDisplayed(logoutLink);
		//return driver.findElement(logoutLink).isDisplayed();
	}
	
	public List<String> getHeadersList() {
		List<WebElement> headerList = eleUtil.waitForElementsVisible(headers, AppConstants.SHORT_TIME_OUT);
		List<String> headersValList = new ArrayList<String>();
		for(WebElement e : headerList) {
			String text = e.getText();
			headersValList.add(text);
		}
		return headersValList;
	}
	
	public SearchResultsPage doSearch(String searchKey) {
		System.out.println("search key: " +searchKey);
		WebElement searchEle = eleUtil.waitForElementVisible(search, AppConstants.DEFAULT_TIME_OUT);				
		eleUtil.doSendKeys(searchEle, searchKey);
		eleUtil.doClick(searchIcon);
		return new SearchResultsPage(driver); 
	}
	
	public LogoutPage logout() {
		if(isLogoutLinkExist()) {
			driver.findElement(logoutLink).click();
			System.out.println("Clicked on logout option");
			return new LogoutPage(driver);
		}
		return new LogoutPage(driver);	}
	
}
