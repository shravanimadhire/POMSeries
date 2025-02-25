package com.qa.opencart.tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Assignment extends BaseTest{
	
	@BeforeClass
	public void AssignmentSetup() {
		homepage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		System.out.println("Login is successfull");
	}

	/*
	 * 1. Login
	 * 2. HomePage
	 * 3. Search
	 * 4. Results Page
	 * 5. Click on MyAccount --> return new HomePage(driver)
	 * 6. Click on Logout --> return new LoginPage(driver)
	 * 7. Check LoginPageTitle
	 */
	
	@Test
	public void assignmentTest() {
		searchResultsPage = homepage.doSearch("macbook");
		homepage = commonsPage.selectAccountOption();
		logoutPage = homepage.logout();
		String actTitle = logoutPage.getLogoutPageTitle();
		Assert.assertEquals(actTitle, "Account Logout","==title is not matched==");
	}

	
}
