package com.mashreq.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class HomePageLoad {
	
	By contactButton = By.xpath("//span[@class='glyphicon-cu glyphicon glyphicon-earphone']/parent::a");
	By newsBox = By.xpath("//div[@class='newsBox']/h3");
	
	
	/*
	 * Launch URL
	 */
	public void launchURL(WebDriver driver, String url ,String expectedTitle) {
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		String actualTitle=driver.getTitle();
		Assert.assertTrue(actualTitle.contentEquals(expectedTitle), "Page Load Failed");
	}
	
	/*
	 * Validation of navigation bar
	 */
	public void validateNavigationBar(WebDriver driver) {
		int countMenu=0;
		String[] navigationBarExpected = {"Personal","Corporate","Business","International","Private Banking","Al Islami","Gold","Mashreq Securities","Mashreq Capital","Mashreq Global Services"};
		List<WebElement> navigationbarContent = driver.findElements(By.xpath("//div[@class='leftLinks']/descendant::li"));
		System.out.println(navigationbarContent.size());
		for(int i=0;i<navigationbarContent.size();i++) {
			String navigationList = navigationbarContent.get(i).getText();
			if(navigationList.equalsIgnoreCase(navigationBarExpected[i])) {
				countMenu++;
			}		
		}
		System.out.println(countMenu);
		Assert.assertTrue(countMenu==9, "Navigation menu Validation failed");
	}
	
	/*
	 * Contact Link Validation
	 */
	
	public void validateContactLink(WebDriver driver) {
		String contactAvailability = driver.findElement(contactButton).getText();
		String expectedTitle = "Contact us | Personal Banking | Mashreq Bank";
		Assert.assertTrue(driver.findElement(contactButton).isDisplayed(), "No Contact link available on home page");
		Actions builder = new Actions(driver);
		Action mouseHoverContact = builder.moveToElement(driver.findElement(contactButton)).build();
		mouseHoverContact.perform();
		driver.findElement(contactButton).click();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		String actualTitle = driver.getTitle();
		Assert.assertTrue(expectedTitle.contains(actualTitle), "Contact page is not loaded");	
	}
	
	/*
	 * Mashreq News Section validation
	 */
	public void validateMashreqNewsSection(WebDriver driver) {
		String expectedNewsText=" Mashreq News";
		String actualNewsText=driver.findElement(newsBox).getText();
		Assert.assertTrue(expectedNewsText.contains(actualNewsText), "Mashreq news is not displayed");
	}

}
