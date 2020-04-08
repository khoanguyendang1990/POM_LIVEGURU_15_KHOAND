package com.liveguru.backend;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import commons.BaseTest;
import commons.Constants;
import commons.PageGeneratorManager;
import pageObjects.backend.CustomerInfoBE_PO;
import pageObjects.backend.EditReviewBE_PO;
import pageObjects.backend.HomeBE_PO;
import pageObjects.backend.LoginBE_PO;
import pageObjects.backend.ManageCustomerBE_PO;
import pageObjects.backend.ReviewBE_PO;
import pageObjects.frontend.HomePO;
import pageObjects.frontend.MyWishListPO;
import pageObjects.frontend.ProductDetailsPO;
import pageObjects.frontend.ProductListPO;
import pageObjects.frontend.RegisterPO;
import reportConfig.ExtentTestManager;

public class Register_Account_FE_Then_Delete_Account_BE extends BaseTest {
	WebDriver driver;
	//FrontEnd
	private HomePO homePO;
	private RegisterPO registerPO;
	private MyWishListPO myWishListPO;
	private ProductListPO productListPO;
	private ProductDetailsPO productDetailsPO;
	
	//BackEnd
	private HomeBE_PO homeBE_PO;
	private LoginBE_PO loginBE_PO;
	private ManageCustomerBE_PO manageCustomerBE_PO;
	private CustomerInfoBE_PO customerInfoBE_PO;
	private ReviewBE_PO reviewBE_PO;
	private EditReviewBE_PO editReviewBE_PO;
	//TC01
	private String firstName="Khoa";
	private String middleName="Dang";
	private String lastName="Nguyen";
	private String email="khoa"+randomNumber()+"@hotmail.com";
	private String password="khoa1234";
	private String confirmPassword="khoa1234";

	//TC_02
	private String titleDetails="Kingsley test review details TV";
	private String titleSummary="Review summary"+randomNumber();
	private String titleCustomerName="Khoa Nguyen";
	private String productName = "LG LCD";
	
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
	public void TC_02_Add_New_Review() {
		ExtentTestManager.startTest("TC_02_Add_New_Review", "Add new review");
		log.info("========Start TC_02_Add_New_Review========");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 1: Open LiveGuru99 site");
		log.info("Step 1: Open LiveGuru99 site");
		homePO.openUrl(Constants.URL_FRONTEND);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 2: Click on TV Menu");
		log.info("Step 2: Click on TV Menu");
		homePO.clickToDynamicLink("TV");
		productListPO = PageGeneratorManager.getProductListPage(driver);

		log.info("Verify Product List TV open correctly");
		verifyEquals(productListPO.getTextPageHeader(), "TV");

		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 3: Click on product: " + productName);
		log.info("Step 3: Click on product: " + productName);
		productListPO.clickToDynamicLink(productName);
		productDetailsPO = PageGeneratorManager.getProductDetailsPage(driver);
		log.info("Verify Product details page " + productName + " open correctly");
		verifyEquals(productDetailsPO.getProductDetailsName(), productName.toUpperCase());
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 4: Click on reviews");
		log.info("Step 4: Click on reviews");
		productDetailsPO.clickToDynamicLink("Add Your Review");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 5: Fill in reviews field");
		log.info("Step 5: Fill in reviews field");
		log.info("Input To Review Detail");
		productDetailsPO.inputToDynamicTextarea("detail", titleDetails);
		
		log.info("Input To Review Summary");
		productDetailsPO.inputToDynamicTextbox("summary_field", titleSummary);
		
		log.info("Input To Name");
		productDetailsPO.inputToDynamicTextbox("nickname_field", titleCustomerName);
		
		log.info("Click Submit button");
		productDetailsPO.clickToDynamicButton("Submit Review");
		
		log.info("Verify review messsage added successfully");
		verifyEquals(productDetailsPO.getTextSuccessMessage(),"Your review has been accepted for moderation.");
		
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Log out");
		log.info("Click on Account menu");
		homePO.clickToDynamicSpanLabel("Account");
		log.info("Log out");
		homePO.clickToDynamicLink("Log Out");
		registerPO = PageGeneratorManager.getRegisterPage(driver);
		
		log.info("========End TC_02_Add_New_Review========");
		ExtentTestManager.endTest();
	}

	@Test(dependsOnMethods = "TC_02_Add_New_Review")
	public void TC_03_Edit_Review() {
		ExtentTestManager.startTest("TC_03_Edit_Review", "Edit review at Backend");
		log.info("========Start TC_03_Edit_Review========");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 1: Open LiveGuru99 Admin site");
		log.info("Step 1: Open LiveGuru99 Admin site");
		loginBE_PO = PageGeneratorManager.getLoginBEPage(driver);
		loginBE_PO.openUrl(Constants.URL_BACKEND);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 2: LoginLiveGuru99 Admin site");
		log.info("Step 2: LoginLiveGuru99 Admin site");
		loginBE_PO.loginSuccessfully(Constants.USERNAME_BACKEND, Constants.PASSWORD_BACKEND);
		
		homeBE_PO = PageGeneratorManager.getHomeBEPage(driver);
		homeBE_PO.closePopup();
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 3: Navigate to Manage Customers");
		log.info("Step 3: Navigate to Customers Reviews - Pending Review");
		homeBE_PO.hoverMouseToDynamicElement("Catalog");
		homeBE_PO.hoverMouseToDynamicElement("Reviews and Ratings");
		homeBE_PO.hoverMouseToDynamicElement("Customer Reviews");
		homeBE_PO.clickToSubMenuByName("Pending Reviews");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 4: Input Email to search Account");
		log.info("Step 4: Input Email to search Account");
		reviewBE_PO = PageGeneratorManager.getReviewBEPage(driver);
		
		log.info("Verify Pending Review page is opened");
		verifyEquals(reviewBE_PO.getReviewPageHeader(), "Pending Reviews");
		
		log.info("Input to Title textbox");
		reviewBE_PO.inputToDynamicTextbox("reviwGrid_filter_title", titleSummary);
		reviewBE_PO.clickToDynamicButton("Search");
		
		log.info("Wait for Loading Mask disappear");
		reviewBE_PO.waitForLoadingMaskDisappear();
		
		log.info("Click on Review");
		reviewBE_PO.editReviewByTitleSummary(titleSummary);
		
		log.info("Verify Edit Review page is opened");
		editReviewBE_PO = PageGeneratorManager.getEditReviewBEPage(driver);
		verifyEquals(editReviewBE_PO.getEditReviewPageHeader(),"Edit Review '"+titleSummary+"'");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 5: Edit review then Save");
		log.info("Step 5: Edit review then Save");
		
		log.info("Edit review details");
		editReviewBE_PO.inputToDynamicTextarea("detail", titleDetails + " updated");
		editReviewBE_PO.clickToDynamicButton("Save Review");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 6: Verify Message Edit Review Successfully");
		log.info("Step 6: Verify Message Edit Review Successfully");
		reviewBE_PO = PageGeneratorManager.getReviewBEPage(driver);
		verifyEquals(reviewBE_PO.getTextSuccessMessage(),"The review has been saved.");
		ExtentTestManager.getTest().log(LogStatus.INFO, "Log out");
		log.info("Log out");
		reviewBE_PO.clickToDynamicLink("Log Out");
		
		log.info("========End TC_03_Edit_Review========");
		ExtentTestManager.endTest();
	}
	
	@Test(dependsOnMethods = "TC_03_Edit_Review")
	public void TC_04_Delete_Review() {
		ExtentTestManager.startTest("TC_04_Delete_Review", "Delete review at Backend");
		log.info("========Start TC_04_Delete_Review========");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 1: Open LiveGuru99 Admin site");
		log.info("Step 1: Open LiveGuru99 Admin site");
		loginBE_PO = PageGeneratorManager.getLoginBEPage(driver);
		loginBE_PO.openUrl(Constants.URL_BACKEND);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 2: LoginLiveGuru99 Admin site");
		log.info("Step 2: LoginLiveGuru99 Admin site");
		loginBE_PO.loginSuccessfully(Constants.USERNAME_BACKEND, Constants.PASSWORD_BACKEND);
		
		homeBE_PO = PageGeneratorManager.getHomeBEPage(driver);
		homeBE_PO.closePopup();
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 3: Navigate to Manage Customers");
		log.info("Step 3: Navigate to Customers Reviews - Pending Review");
		homeBE_PO.hoverMouseToDynamicElement("Catalog");
		homeBE_PO.hoverMouseToDynamicElement("Reviews and Ratings");
		homeBE_PO.hoverMouseToDynamicElement("Customer Reviews");
		homeBE_PO.clickToSubMenuByName("Pending Reviews");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 4: Input Email to search Account");
		log.info("Step 4: Input Email to search Account");
		reviewBE_PO = PageGeneratorManager.getReviewBEPage(driver);
		
		log.info("Verify Pending Review page is opened");
		verifyEquals(reviewBE_PO.getReviewPageHeader(), "Pending Reviews");
		
		log.info("Input to Title textbox");
		reviewBE_PO.inputToDynamicTextbox("reviwGrid_filter_title", titleSummary);
		reviewBE_PO.clickToDynamicButton("Search");
		
		log.info("Wait for Loading Mask disappear");
		reviewBE_PO.waitForLoadingMaskDisappear();
		
		log.info("Click on Review");
		reviewBE_PO.editReviewByTitleSummary(titleSummary);
		
		log.info("Verify Edit Review page is opened");
		editReviewBE_PO = PageGeneratorManager.getEditReviewBEPage(driver);
		verifyEquals(editReviewBE_PO.getEditReviewPageHeader(),"Edit Review '"+titleSummary+"'");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 5: Delete review");
		log.info("Step 5: Delete review");
		
		log.info("Delete review");
		editReviewBE_PO.clickToDynamicButton("Delete Review");
		editReviewBE_PO.acceptAlert();
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 6: Verify Message Delete Review Successfully and review doesn't show in Review grid");
		log.info("Step 6: Verify Message Delete Review Successfully and review doesn't show in Review grid");
		reviewBE_PO = PageGeneratorManager.getReviewBEPage(driver);
		
		log.info("Verify Message Delete Review Successfully");
		verifyEquals(reviewBE_PO.getTextSuccessMessage(),"The review has been deleted");
		
		log.info("Verify review doesn't show in Review grid");
		verifyTrue(reviewBE_PO.isReviewNotDisplayInGrid(titleSummary));
		ExtentTestManager.getTest().log(LogStatus.INFO, "Log out");
		log.info("Log out");
		reviewBE_PO.clickToDynamicLink("Log Out");
		
		log.info("========End TC_04_Delete_Review========");
		ExtentTestManager.endTest();
		
	}
	
	@Test(dependsOnMethods = "TC_04_Delete_Review")
	public void TC_05_Delete_Account_Back_End() {
		ExtentTestManager.startTest("TC_05_Delete_Account_Back_End", "Verify account created successfully then delete at Backend");
		log.info("========Start TC_05_Delete_Account_Back_End========");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 1: Open LiveGuru99 Admin site");
		log.info("Step 1: Open LiveGuru99 Admin site");
		loginBE_PO = PageGeneratorManager.getLoginBEPage(driver);
		loginBE_PO.openUrl(Constants.URL_BACKEND);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 2: LoginLiveGuru99 Admin site");
		log.info("Step 2: LoginLiveGuru99 Admin site");
		loginBE_PO.loginSuccessfully(Constants.USERNAME_BACKEND, Constants.PASSWORD_BACKEND);
		
		homeBE_PO = PageGeneratorManager.getHomeBEPage(driver);
		homeBE_PO.closePopup();
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 3: Navigate to Manage Customers");
		log.info("Step 3: Navigate to Manage Customers");
		homeBE_PO.hoverMouseToDynamicElement("Customers");
		homeBE_PO.clickToSubMenuByName("Manage Customers");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 4: Input Email to search Account");
		log.info("Step 4: Input Email to search Account");
		manageCustomerBE_PO = PageGeneratorManager.getManageCustomerBEPage(driver);
		
		log.info("Verify Manage Customers page is opened");
		verifyTrue(manageCustomerBE_PO.isManageCustomerPageDisplayed());
		
		log.info("Verify Manage Customers page is opened");
		manageCustomerBE_PO.inputToDynamicTextbox("customerGrid_filter_email", email);
		manageCustomerBE_PO.clickToDynamicButton("Search");
		
		log.info("Wait for Loading Mask disappear");
		manageCustomerBE_PO.waitForLoadingMaskDisappear();
		
		log.info("Click on Email");
		manageCustomerBE_PO.editUserByEmail(email);
		
		log.info("Verify Customer Information page is opened");
		customerInfoBE_PO = PageGeneratorManager.getCustomerInfoBEPage(driver);
		verifyTrue(customerInfoBE_PO.isCustomerInfoPageDisplayed());
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 5: Delete Customer Account");
		log.info("Step 5: Delete Customer Account");
		log.info("Click on Delete Customer");
		customerInfoBE_PO.clickToDynamicButton("Delete Customer");
		customerInfoBE_PO.acceptAlert();
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 6: Verify Message Delete Customer Account Successfully");
		log.info("Step 6: Verify Message Delete Customer Account Successfully");
		manageCustomerBE_PO = PageGeneratorManager.getManageCustomerBEPage(driver);
		verifyEquals(manageCustomerBE_PO.getTextSuccessMessage(),"The customer has been deleted.");
		log.info("Verify review doesn't show in Review grid");
		verifyTrue(manageCustomerBE_PO.isReviewNotDisplayInGrid(email));
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Log out");
		log.info("Log out");
		manageCustomerBE_PO.clickToDynamicLink("Log Out");
		
		log.info("========End TC_05_Delete_Account_Back_End========");
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
