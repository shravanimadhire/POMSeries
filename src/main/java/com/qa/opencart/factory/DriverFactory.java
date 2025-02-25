package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.exception.FrameworkException;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	public static String highlight;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	public WebDriver initDriver(Properties prop) {
		String browserName = prop.getProperty("browser");
		System.out.println("browser name is :" + browserName);
		
		highlight = prop.getProperty("highlight");
		optionsManager = new OptionsManager(prop);
		
		switch (browserName) {
		case "chrome":
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			break;
		case "firefox":
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
//			driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			break;
		case "edge":
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			//driver = new EdgeDriver(optionsManager.getEdgeOptions());
			break;
		case "safari":
			tlDriver.set(new SafariDriver());
			//driver = new SafariDriver();
			break;
		default:
			System.out.println("plz pass the right browser name..." +browserName);
			throw new FrameworkException("===invalid browser name===");
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}
	
	public static WebDriver getDriver() {
		return tlDriver.get();
	}
	
	public Properties initProp() {
		String envName = System.getProperty("env");
		System.out.println("running test suite on env: " +envName);
		prop = new Properties();
		FileInputStream ip = null;
		try {
			if(envName == null) {
				System.err.println("no env is passed, hence running test suite on QA env...");
				ip = new FileInputStream(AppConstants.CONFIG_QA_PROP_FILE_PATH);
			}else {
				switch(envName.trim().toLowerCase()) {
				case "qa":
					ip = new FileInputStream(AppConstants.CONFIG_QA_PROP_FILE_PATH);
					break;
				case "dev":
					ip = new FileInputStream(AppConstants.CONFIG_DEV_PROP_FILE_PATH);
					break;
				case "stage":
					ip = new FileInputStream(AppConstants.CONFIG_STAGE_PROP_FILE_PATH);
					break;
				case "uat":
					ip = new FileInputStream(AppConstants.CONFIG_UAT_PROP_FILE_PATH);
					break;
				case "prod":
					ip = new FileInputStream(AppConstants.CONFIG_PROD_FILE_PATH);
					break;
				default:
					System.err.println("plz pass the right env name... " +envName);
					throw new FrameworkException("===INVALID ENV===");
				}
				
			}
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
}
