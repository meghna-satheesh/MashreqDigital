package com.mashreq.runner;


import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.mashreq.pages.ContactPageLoad;
import com.mashreq.pages.HomePageLoad;
import com.mashreq.reusablefunction.DriverInitialization;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class BaseTest {
	
	WebDriver driver;
	DriverInitialization initializeDriver=new DriverInitialization();
	HomePageLoad homePage = new HomePageLoad();
	ContactPageLoad contactPage = new ContactPageLoad();
	
	@BeforeClass
	  public void beforeClass() throws IOException {
		  String browserValue = initializeDriver.getpropertyValue("browser");
		  driver = initializeDriver.setupDriver(browserValue);
	  }
	
	@Test
	public void loginValidation() throws IOException {
		  String baseURL = initializeDriver.getpropertyValue("url"); 
		  String expectedTitle = initializeDriver.getpropertyValue("hometitleExpected");
		  homePage.launchURL(driver, baseURL , expectedTitle);  		  
	  } 
	
	@Test(dependsOnMethods= {"loginValidation"})	  
	public void navigationMenuCheck() {
		homePage.validateNavigationBar(driver);
	}
	
	@Test(dependsOnMethods= {"loginValidation"})
	public void mashreqNewsDisplayCheck() {
		homePage.validateMashreqNewsSection(driver);
	}
	
	@Test(dependsOnMethods= {"loginValidation"})
	public void contactLinkCheck() {
		homePage.validateContactLink(driver);
	}
	
	@Test(dependsOnMethods= {"loginValidation"})
	public void contactPageErrorMessageValidation() {
		contactPage.validateContactPageErrorMessage(driver);
	}
	
	@Test(dependsOnMethods= {"loginValidation"})
	public void  selectSubProductFieldCheck() {
		contactPage.validateSelectSubproductField(driver);
	}
	
	@Test(dependsOnMethods= {"loginValidation"})
	public void  subProductFieldwithValueCheck() {
		contactPage.validateSubproductFieldValue(driver);
	}
	
	@Test(dependsOnMethods= {"loginValidation"})
	public void  mobbileNumberCheck() {
		contactPage.validateMobileNumberField(driver);
	}
  

	@AfterClass
	public void afterClass() {
		driver.close();
	}

}
