package com.qa.opencart.pages;

import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class LogoutPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	
	public LogoutPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public String getLogoutPageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstants.LOGOUT_PAGE_TITLE, AppConstants.DEFAULT_TIME_OUT);
		//String title = driver.getTitle();
		System.out.println("logout page title ===>" +title);
		return title;
	}
}
