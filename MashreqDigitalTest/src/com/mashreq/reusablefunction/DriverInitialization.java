package com.mashreq.reusablefunction;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverInitialization {
	
	WebDriver driver;
	
	/*
	 * Get values from properties file
	 */
	public String getpropertyValue(String field) throws IOException {
		Properties globalProperty = new Properties();
		InputStream inputGlobal = new FileInputStream("Resources\\Global.properties");
		globalProperty.load(inputGlobal);
		String fieldValue=globalProperty.getProperty(field);
		return fieldValue;
	}
	
	/*
	 * Web driver setup
	 */
	public WebDriver setupDriver(String usedBrowser) throws IOException {	
		System.out.println(usedBrowser);
		if(usedBrowser.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\lenovo\\eclipse-workspace\\MashreqDigital\\Driver\\chromedriver.exe");
			driver=new ChromeDriver();
		}
		else if(usedBrowser.equalsIgnoreCase("InternetExplorer")) {
			DesiredCapabilities capabilitiesIE = DesiredCapabilities.internetExplorer();
			capabilitiesIE.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			System.setProperty("webdriver.ie.driver", "C:\\Users\\lenovo\\eclipse-workspace\\MashreqDigital\\Driver\\IEDriver.exe");
			driver=new InternetExplorerDriver(capabilitiesIE);
		}
		else if(usedBrowser.equalsIgnoreCase("Firefox")) {
			driver=new FirefoxDriver();
		}
		else {
			return null;
		}
		return driver;
	}	
	 

}
