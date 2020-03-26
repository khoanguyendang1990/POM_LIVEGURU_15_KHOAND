package com.liveguru.frontend;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import commons.BaseTest;
import commons.Constants;
import commons.PageGeneratorManager;
import pageObjects.frontend.AccountInformationPO;
import pageObjects.frontend.HomePO;
import pageObjects.frontend.MyWishListPO;
import pageObjects.frontend.RegisterPO;
import reportConfig.ExtentTestManager;

public class Register_Account extends BaseTest {
	WebDriver driver;
	private HomePO homePO;
	private RegisterPO registerPO;
	private MyWishListPO myWishListPO;
	private AccountInformationPO accountInformationPO;
	
	//TC01
	private String firstName="Khoa";
	private String middleName="Dang";
	private String lastName="Nguyen";
	private String email="khoa"+randomNumber()+"@hotmail.com";
	private String password="khoa1234";
	private String confirmPassword="khoa1234";

	
	@Test
	public void TC_01_Register_success_to_system() {
		ExtentTestManager.startTest("TC_01_Register_success_to_system", "Register success to system");
		log.info("========Start TC_01_Register_success_to_system========");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 1: Open LiveGuru99 site");
		log.info("Step 1: Open LiveGuru99 site");
		homePO.openUrl(Constants.URL_FRONTEND);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 2: Click on Account menu");
		log.info("Step 2: Click on Account menu");
		homePO.clickToDynamicSpanLabel("Account");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 3: Choose Register link");
		log.info("Step 3: Choose Register link");
		homePO.clickToDynamicLink("Register");
		registerPO = PageGeneratorManager.getRegisterPage(driver);
		verifyEquals(registerPO.getTextPageHeader(), "CREATE AN ACCOUNT");
		
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 4: Input all valid data to form");
		log.info("Step 4: Input all valid data to form");
		
		log.info("Input to FirstName: " + firstName);
		registerPO.inputToDynamicTextbox("firstname", firstName);
		
		log.info("Input to MiddleName: " + middleName);
		registerPO.inputToDynamicTextbox("middlename", middleName);
		
		log.info("Input to LastName: " + lastName);
		registerPO.inputToDynamicTextbox("lastname", lastName);
		
		log.info("Input to Email: " + email);
		registerPO.inputToDynamicTextbox("email_address", email);
		
		log.info("Input to Password: " + password);
		registerPO.inputToDynamicTextbox("password", password);
		
		log.info("Input to Confirm Password: "+confirmPassword);
		registerPO.inputToDynamicTextbox("confirmation", confirmPassword);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 5: Click to Register button");
		log.info("Step 5: Click to Register button");
		registerPO.clickToDynamicButton("Register");
		myWishListPO = PageGeneratorManager.getWishListPage(driver);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verify text displayed after register successfully");
		log.info("Verify text displayed after registerd successfully");
		verifyEquals(myWishListPO.getTextSuccessMessage(),"Thank you for registering with Main Website Store.");
		
		log.info("========End TC_01_Register_success_to_system========");
		ExtentTestManager.endTest();
	}
	
	@Test(dependsOnMethods = "TC_01_Register_success_to_system")
	public void TC_02_Verify_user_information_is_correct_after_registered_successfully() {
		ExtentTestManager.startTest("TC_02_Verify_user_information_is_correct_after_registered_successfully", "Verify user information is correct after registered successfully");
		log.info("========Start TC_02_Verify_user_information_is_correct_after_registered_successfully========");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 1: Open LiveGuru99 site");
		log.info("Step 1: Open LiveGuru99 site");
		homePO.openUrl(Constants.URL_FRONTEND);
		
		log.info("Click on Account menu");
		homePO.clickToDynamicSpanLabel("Account");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 2: Choose My Account link");
		log.info("Step 2: Choose My Account link");
		homePO.clickToDynamicLink("My Account");
		registerPO = PageGeneratorManager.getRegisterPage(driver);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 3: Open Account Information Page");
		log.info("Step 3: Open Account Information Page");
		homePO.clickToDynamicLink("Account Information");
		accountInformationPO = PageGeneratorManager.getAccountInformationPage(driver);
		verifyEquals(accountInformationPO.getTextPageHeader(), "EDIT ACCOUNT INFORMATION");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Verify data in 3 fields FirstName, LastName, Email are correctly");
		log.info("Verify data in 3 fields FirstName, LastName, Email are correctly");
		
		log.info("Verify FirstName: " + firstName);
		verifyEquals(accountInformationPO.getDynamicTextboxAttributeValue("firstname","value"),firstName);
		
		log.info("Verify LastName: " + lastName);
		verifyEquals(accountInformationPO.getDynamicTextboxAttributeValue("lastname","value"),lastName);
		
		log.info("Verify Email: " + email);
		verifyEquals(accountInformationPO.getDynamicTextboxAttributeValue("email","value"),email);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 4: Log out");
		log.info("Step 4: Log out");
		log.info("Click on Account menu");
		homePO.clickToDynamicSpanLabel("Account");
		
		homePO.clickToDynamicLink("Log Out");
		registerPO = PageGeneratorManager.getRegisterPage(driver);
		
		log.info("========End TC_02_Verify_user_information_is_correct_after_registered_successfully========");
		ExtentTestManager.endTest();
	}

	@BeforeClass
	@Parameters("browserName")
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		homePO = PageGeneratorManager.getHomePage(driver);
		
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

}
