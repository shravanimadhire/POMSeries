package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.constants.AppError;


public class LoginPageTest extends BaseTest{

	@Test	
	public void loginPageTitleTest() {
		String actTitle = loginpage.getLoginPageTitle();
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE, AppError.TITLE_NOT_FOUND_ERROR);
	}
	
	@Test
	public void loginPageURLTest() {
		String actUrl = loginpage.getLoginPageURL();
		Assert.assertTrue(actUrl.contains(AppConstants.LOGIN_PAGE_URL_FRACTION), AppError.URL_NOT_FOUND_ERROR);
	}
	
	@Test
	public void forgotPwdLinkExistTest() {
		Assert.assertTrue(loginpage.isForgotPwdLinkExist(), AppError.ELEMENT_NOT_FOUND_ERROR);	
	}
	
	@Test(priority = Integer.MAX_VALUE)
	public void loginTest() {
		homepage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(homepage.getHomePageTitle(), AppConstants.HOME_PAGE_TITLE, AppError.TITLE_NOT_FOUND_ERROR);
	}
	
	@Test
	public void logoTest() {
		Assert.assertTrue(commonsPage.isLogoDisplayed(),AppError.LOGO_NOT_FOUND_ERROR);	
	}
	
	@DataProvider
	public Object[][] getFooterData() {
		return new Object[][] {
			{"About Us"},
			{"Contact Us"},
			{"Specials"},
			{"Order History"}	
		};
	}
	
	@Test(dataProvider = "getFooterData")
	public void footerTest(String footerlink) {
		Assert.assertTrue(commonsPage.checkFooterLink(footerlink));
	}
}









