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
import pageObjects.frontend.CheckOutPO;
import pageObjects.frontend.HomePO;
import pageObjects.frontend.LoginPO;
import pageObjects.frontend.MyWishListPO;
import pageObjects.frontend.ProductListPO;
import pageObjects.frontend.RegisterPO;
import pageObjects.frontend.ShoppingCartPO;
import reportConfig.ExtentTestManager;

public class Purchase_Product_Function_FirstTime extends BaseTest {
	WebDriver driver;
	private HomePO homePO;
	private RegisterPO registerPO;
	private MyWishListPO myWishListPO;
	
	private ProductListPO productListPO;
	private ShoppingCartPO shoppingCartPO;
	private LoginPO loginPO;
	private CheckOutPO checkOutPO;
	//TC01
	private String firstName="Khoa";
	private String middleName="Dang";
	private String lastName="Nguyen";
	private String email="khoa"+randomNumber()+"@hotmail.com";
	private String password="khoa1234";
	private String confirmPassword="khoa1234";

	private String productName = "Sony Xperia";
	
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
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Log out");
		log.info("Log out");
		log.info("Click on Account menu");
		homePO.clickToDynamicSpanLabel("Account");
		
		homePO.clickToDynamicLink("Log Out");
		registerPO = PageGeneratorManager.getRegisterPage(driver);
		
		log.info("========End TC_01_Register_success_to_system========");
		ExtentTestManager.endTest();
	}

	@Test(dependsOnMethods = "TC_01_Register_success_to_system")
	public void TC_09_Verify_user_is_able_to_purchase_product() {
		ExtentTestManager.startTest("TC_06_Verify_that_you_are_able_to_compare_two_products", "Verify that you are able to compare two products");
		log.info("========Start TC_06_Verify_that_you_are_able_to_compare_two_products========");

		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 1: Open LiveGuru99 site and login");
		log.info("Step 1: Open LiveGuru99 site and login");
		homePO.openUrl(Constants.URL_FRONTEND);

		log.info("Click on Account menu");
		homePO.clickToDynamicSpanLabel("Account");

		ExtentTestManager.getTest().log(LogStatus.INFO, "Login Successfully");
		log.info("Login Successfully");
		homePO.clickToDynamicLink("Log In");
		loginPO = PageGeneratorManager.getLoginPage(driver);
//		loginPO.loginSuccessfully(Constants.EMAIL_USERNAME, Constants.PASSWORD);
		loginPO.loginSuccessfully(email, password);

		homePO = PageGeneratorManager.getHomePage(driver);
		verifyEquals(homePO.getTextPageHeader().toUpperCase(), "MY DASHBOARD");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 2: Click on Mobile Menu and Add to Cart");
		log.info("Step 2: Click on Mobile Menu and Add to Cart");
		homePO.clickToDynamicLink("Mobile");
		productListPO = PageGeneratorManager.getProductListPage(driver);

		log.info("Verify Product List Mobile open correctly");
		verifyEquals(productListPO.getTextPageHeader(), "MOBILE");

		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 4: Add Sony Xperia to Cart");
		log.info("Step 4: Add Sony Xperia to Cart");
		productListPO.addProductToCartByName(productName);
		shoppingCartPO = PageGeneratorManager.getShoppingCartPage(driver);

		log.info("Verify Shopping Cart open correctly");
		verifyEquals(shoppingCartPO.getTextPageHeader(), "SHOPPING CART");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 6: Enter general shipping country, state/province and zip for the shipping cost estimate");
		log.info("Step 6: Enter general shipping country, state/province and zip for the shipping cost estimate");
		shoppingCartPO.selectDynamicDropdownList("country","United States");
		shoppingCartPO.selectDynamicDropdownList("region_id","New York");
		shoppingCartPO.inputToDynamicTextbox("postcode", "543432");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 7: Click Estimate Button");
		log.info("Step 7: Click Estimate Button");
		shoppingCartPO.clickToDynamicButton("Estimate");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 8: Verify Shipping cost generated");
		log.info("Step 8: Verify Shipping cost generated");
		verifyEquals(shoppingCartPO.getFlatRateShippingCost(),"$5.00");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 9: Select Shipping Cost, Update Total");
		log.info("Step 9: Select Shipping Cost, Update Total");
		shoppingCartPO.clickToDynamicRadioButton("s_method_flatrate_flatrate");
		shoppingCartPO.clickToDynamicButton("Update Total");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 10: Verify shipping cost is added to total");
		log.info("Step 10: Verify shipping cost is added to total");
		verifyEquals(shoppingCartPO.getTextShippingInfo(),"SHIPPING & HANDLING (FLAT RATE - FIXED)");
		verifyEquals(shoppingCartPO.getTextShippingValue(),"$5.00");
		verifyEquals(shoppingCartPO.getTextGrandTotalPrice(),"$105.00");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 11: Click 'Proceed to Checkout'");
		log.info("Step 11: Click 'Proceed to Checkout'");
		shoppingCartPO.clickToDynamicButton("Proceed to Checkout");
		checkOutPO = PageGeneratorManager.getCheckOutPage(driver);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 12: Enter Billing Information, and click Continue");
		log.info("Step 12: Enter Billing Information, and click Continue");
		
		log.info("Input To Address");
		checkOutPO.inputToDynamicTextbox("billing:street1", "54 Tran Quang Co");
		
		log.info("Input To City");
		checkOutPO.inputToDynamicTextbox("billing:city", "New York");
		
		log.info("Select State");
		checkOutPO.selectDynamicDropdownList("billing:region_id", "New York");
		
		log.info("Input To ZIP");
		checkOutPO.inputToDynamicTextbox("billing:postcode", "543432");
		
		log.info("Select Country");
		checkOutPO.selectDynamicDropdownList("billing:country_id","United States");
		
		log.info("Input To Phone");
		checkOutPO.inputToDynamicTextbox("billing:telephone", "0938992805");
		
		log.info("Click on ship to this address");
		checkOutPO.clickToDynamicRadioButton("billing:use_for_shipping_yes");
		
		log.info("Click To Continue button");
		checkOutPO.clickToContrinueButtonOnBillingMethod();
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 13: In Shipping Method, Click Continue");
		log.info("Step 13: In Shipping Method, Click Continue");
		checkOutPO.clickToContrinueButtonOnShippingMethod();
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 14: In Payment Information select 'Check/Money Order' radio button. Click Continue");
		log.info("Step 14: In Payment Information select 'Check/Money Order' radio button. Click Continue");
		checkOutPO.clickToDynamicRadioButton("p_method_checkmo");
		checkOutPO.clickToContrinueButtonOnPaymentInfo();
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 15: Click PLACE ORDER Button");
		log.info("Step 15: Click PLACE ORDER Button");
		checkOutPO.clickToDynamicButton("Place Order");
		homePO=PageGeneratorManager.getHomePage(driver);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 16: Verify Oder is generated. Note the order number");
		log.info("Step 16: Verify Oder is generated. Note the order number");
		verifyEquals(homePO.getOrderSuccessMessage(), "YOUR ORDER HAS BEEN RECEIVED.");
		
		
		String orderNumber = homePO.getOrderNumber();
		ExtentTestManager.getTest().log(LogStatus.INFO, "Order number is: " + orderNumber);
		log.info("Order number is: " + orderNumber);
		
		log.info("========End TC_09_Verify_user_is_able_to_purchase_product========");
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
