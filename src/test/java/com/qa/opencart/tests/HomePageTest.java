package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.constants.AppError;

public class HomePageTest extends BaseTest {

	@BeforeClass
	public void homePageSetup() {
		homepage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test	
	public void HomePageTitleTest() {
		String actTitle = homepage.getHomePageTitle();
		Assert.assertEquals(actTitle, AppConstants.HOME_PAGE_TITLE, AppError.TITLE_NOT_FOUND_ERROR);
	}
	
	@Test
	public void HomePageURLTest() {
		String actUrl = homepage.getHomePageURL();
		Assert.assertTrue(actUrl.contains(AppConstants.HOME_PAGE_URL_FRACTION), AppError.URL_NOT_FOUND_ERROR);
	}

	@Test
	public void logoutLinkExistTest() {
		Assert.assertTrue(homepage.isLogoutLinkExist(), AppError.ELEMENT_NOT_FOUND_ERROR);	
	} 
	
	@Test
	public void headersTest() {
		List<String> actualHeaders = homepage.getHeadersList();
		System.out.println("home page headers: ==> " +actualHeaders);
	}
	
	@DataProvider
	public Object[][] getSearchData() {
		return new Object[][] {
			{"macbook",3},
			{"imac",1},
			{"samsung",2},
			{"canon",1},
			{"airtel",0}
		};
	}
	
	@Test(dataProvider="getSearchData")
	public void searchTest(String searchKey, int resultCount) {
		searchResultsPage = homepage.doSearch(searchKey);
		Assert.assertEquals(searchResultsPage.getProductResultsCount(), resultCount);
	}
	
	
}
