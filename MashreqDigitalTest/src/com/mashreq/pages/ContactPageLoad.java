package com.mashreq.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class ContactPageLoad {
	
	WebDriver driver;
	
	By iamLookingTo = By.xpath("//select[@id='reachoutforproduct']");
	By complaintInquiryService = By.xpath("//select[@id='compInqServ']");
	By product = By.xpath("//select[@id='need']");
	By subProduct = By.xpath("//select[@id='product']");
	By emirate = By.xpath("//select[@id='emirate']");
	By branch = By.xpath("//select[@id='branch']");
	By prefLanguage = By.xpath("//select[@id='prefLang']");
	By customerStatus = By.xpath("//select[@id='curStatus']");
	By firstName = By.xpath("//input[@id='firstName']");
	By lastName = By.xpath("//input[@id='lastName']");
	By email = By.xpath("//input[@id='email']");
	By countryCode = By.xpath("//select[@id='mob1']");
	By mobileNumber = By.xpath("//input[@id='mobile']");
	By commentBox = By.xpath("//textarea[@id='comments']");
	By submitButton = By.xpath("//button[@id='btnSubmit']");
	By restButton = By.xpath("//button[@id='btnReset']");
	
	/*
	 * Error message validation in contact page 
	 */
	public void validateContactPageErrorMessage(WebDriver driver) {
		driver.findElement(submitButton).click();
		if((driver.findElement(iamLookingTo).getText()).contains("I am looking to...") || driver.findElement(iamLookingTo).getText().isEmpty()) {
			Assert.assertTrue((driver.findElement(iamLookingTo).getAttribute("class")).contains("ng-empty"), "Failed to display error message");
		}
		else if((driver.findElement(complaintInquiryService).getText()).contains("Complaint/Inquiry/Service Type") || driver.findElement(complaintInquiryService).getText().isEmpty()) {
			Assert.assertTrue((driver.findElement(complaintInquiryService).getAttribute("class")).contains("ng-empty"), "Failed to display error message");
		}
		else if((driver.findElement(product).getText()).contains("-- Select Product--") || driver.findElement(product).getText().isEmpty()) {
			Assert.assertTrue((driver.findElement(product).getAttribute("class")).contains("ng-empty"), "Failed to display error message");
		}
		else if((driver.findElement(subProduct).getText()).contains("-- Select Sub Product --") || driver.findElement(subProduct).getText().isEmpty()) {
			Assert.assertTrue((driver.findElement(subProduct).getAttribute("class")).contains("ng-empty"), "Failed to display error message");
		}
		else if((driver.findElement(emirate).getText()).contains("-- Select Emirate --") || driver.findElement(emirate).getText().isEmpty()) {
			Assert.assertTrue((driver.findElement(emirate).getAttribute("class")).contains("ng-empty"), "Failed to display error message");
		}
		else if((driver.findElement(branch).getText()).contains("-- Select Branch --") || driver.findElement(branch).getText().isEmpty()) {
			Assert.assertTrue((driver.findElement(branch).getAttribute("class")).contains("ng-empty"), "Failed to display error message");
		}
		else if((driver.findElement(firstName).getText()).isEmpty()) {
			Assert.assertTrue((driver.findElement(firstName).getAttribute("class")).contains("ng-empty"), "Failed to display error message");
		}
		else if((driver.findElement(email).getText()).isEmpty()) {
			Assert.assertTrue((driver.findElement(email).getAttribute("class")).contains("ng-empty"), "Failed to display error message");
		}
		else if((driver.findElement(countryCode).getText()).isEmpty()) {
			Assert.assertTrue((driver.findElement(countryCode).getAttribute("class")).contains("ng-empty"), "Failed to display error message");
		}
		else if((driver.findElement(mobileNumber).getText()).isEmpty()) {
			Assert.assertTrue((driver.findElement(mobileNumber).getAttribute("class")).contains("ng-empty"), "Failed to display error message");
		}
	}
	
	/*
	 * I am checking to field validation
	 */
	public void validateIamCheckingToField(WebDriver driver) {
		Select iamCheckingSelect = new Select(driver.findElement(iamLookingTo));
		List<WebElement> iamCheckingList=iamCheckingSelect.getOptions();
		Assert.assertTrue((iamCheckingList.size()-1)==4, "Options are not available in the dropdown");
	}
	
	/*
	 * Sub Product field validation after contact page load
	 */
	public void validateSelectSubproductField(WebDriver driver) {	
		Select subProductSelect = new Select(driver.findElement(subProduct));
		List<WebElement> subProductList1=subProductSelect.getOptions();
		Assert.assertTrue((subProductList1.get(0).getText()).equalsIgnoreCase("-- Select Sub Product --") && subProductList1.size()
				==1,"Values getting displayed in the Sub Product dropdown");
	}
	
	/*
	 * Validation of Sub Product field with value
	 */
	public void validateSubproductFieldValue(WebDriver driver) {
		String[] suProducts= {"Auto Loan","Home Loan Non-UAE Resident","Home Loan UAE Resident","Personal Loan For Emiratis","Personal Loan for Expats","Commercial Mortgage Loans UAE Residents"};
		driver.findElement(product).sendKeys("Loans");
		int countSubproduct=0;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Select subProductSelect = new Select(driver.findElement(subProduct));
		List<WebElement> subProductList1=subProductSelect.getOptions();
		for(int i=0;i<subProductList1.size();i++) {
			String subProductList1Value=subProductList1.get(i).getText();
			if(subProductList1Value.equalsIgnoreCase(suProducts[i])) {
				countSubproduct++;
			}
			Assert.assertTrue(countSubproduct==6, "Sub Product validation failed");
		}
	}		
	/*
	 * Mobile number field should contain more than 7 digits
	 */
	public void validateMobileNumberField(WebDriver driver) {
		driver.findElement(mobileNumber).sendKeys("123456");
		Assert.assertTrue((driver.findElement(mobileNumber).getAttribute("class").contains("ng-invalid")),"Error message should be displayed");
	}

}
