package com.liveguru.frontend;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import commons.BaseTest;
import commons.Constants;
import commons.PageGeneratorManager;
import pageObjects.frontend.CheckOutPO;
import pageObjects.frontend.CompareProductPO;
import pageObjects.frontend.HomePO;
import pageObjects.frontend.LoginPO;
import pageObjects.frontend.ProductDetailsPO;
import pageObjects.frontend.ProductListPO;
import pageObjects.frontend.ShoppingCartPO;
import reportConfig.ExtentTestManager;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class Product_Function extends BaseTest {

	WebDriver driver;
	private HomePO homePO;
	private ProductListPO productListPO;
	private ProductDetailsPO productDetailsPO;
	private ShoppingCartPO shoppingCartPO;
	private CompareProductPO compareProductPO;
	private LoginPO loginPO;
	private CheckOutPO checkOutPO;
	// TC_03
	private String productName = "Sony Xperia";

	// TC_04
	private String couponCode = "GURU50";

	// TC_06
	private String productName1 = "Sony Xperia";
	private String productName1_price = "$100.00";
	private String productName1_image = "xperia.jpg";

	private String productName2 = "IPhone";
	private String productName2_price = "$500.00";
	private String productName2_image = "iphone.png";

	@Test
	public void TC_03_Verify_that_cost_of_product_in_list_page_and_details_page_are_equal() {
		ExtentTestManager.startTest("TC_03_Verify_that_cost_of_product_in_list_page_and_details_page_are_equal", "Verify that cost of product in list page and details page are equal");
		log.info("========Start TC_03_Verify_that_cost_of_product_in_list_page_and_details_page_are_equal========");

		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 1: Open LiveGuru99 site");
		log.info("Step 1: Open LiveGuru99 site");
		homePO.openUrl(Constants.URL_FRONTEND);

		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 2: Click on Mobile Menu");
		log.info("Step 2: Click on Mobile Menu");
		homePO.clickToDynamicLink("Mobile");
		productListPO = PageGeneratorManager.getProductListPage(driver);

		log.info("Verify Product List Mobile open correctly");
		verifyEquals(productListPO.getTextPageHeader(), "MOBILE");

		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 3: In the list of all mobiles, get cost of Sony Xperia mobile");
		log.info("Step 3: In the list of all mobiles, get cost of Sony Xperia mobile");
		String productPriceAtProductList = productListPO.getCostOfProductByName(productName);

		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 4: Click on product: " + productName);
		log.info("Step 4: Click on product: " + productName);
		productListPO.clickToDynamicLink(productName);
		productDetailsPO = PageGeneratorManager.getProductDetailsPage(driver);
		log.info("Verify Product details page " + productName + " open correctly");
		verifyEquals(productDetailsPO.getProductDetailsName(), productName.toUpperCase());

		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 5: Get cost " + productName + " mobile from detail page");
		log.info("Step 5: Get cost " + productName + " mobile from detail page");
		String productPriceAtProductDetails = productDetailsPO.getCostOfProduct();

		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 6: Compare value in Step 3 and 5. Product value in list and detais page should be equal");
		log.info("Step 6: Compare value in Step 3 and 5. Product value in list and detais page should be equal");
		verifyEquals(productPriceAtProductDetails, productPriceAtProductList);

		log.info("========End TC_03_Verify_that_cost_of_product_in_list_page_and_details_page_are_equal========");
		ExtentTestManager.endTest();
	}

	@Test
	public void TC_04_Verify_Discount_Coupon_works_correctly() {
		ExtentTestManager.startTest("TC_04_Verify_Discount_Coupon_works_correctly", "Verify Discount Coupon works correctly");
		log.info("========Start TC_04_Verify_Discount_Coupon_works_correctly========");

		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 1: Open LiveGuru99 site");
		log.info("Step 1: Open LiveGuru99 site");
		homePO.openUrl(Constants.URL_FRONTEND);

		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 2: Click on Mobile Menu and Add Iphone to Cart");
		log.info("Step 2: Click on Mobile Menu and Add Iphone to Cart");
		homePO.clickToDynamicLink("Mobile");
		productListPO = PageGeneratorManager.getProductListPage(driver);

		log.info("Verify Product List Mobile open correctly");
		verifyEquals(productListPO.getTextPageHeader(), "MOBILE");

		log.info("Add Sony Xperia to Cart");
		productListPO.addProductToCartByName(productName);
		shoppingCartPO = PageGeneratorManager.getShoppingCartPage(driver);

		log.info("Verify Shopping Cart open correctly");
		verifyEquals(shoppingCartPO.getTextPageHeader(), "SHOPPING CART");

		ExtentTestManager.getTest().log(LogStatus.INFO, "Verify " + productName + " was added to your shopping cart");
		log.info("Verify " + productName + " was added to your shopping cart");
		verifyEquals(shoppingCartPO.getTextSuccessMessage(), productName + " was added to your shopping cart.");

		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 3: Enter Coupon Code");
		log.info("Step 3: Enter Coupon Code");

		log.info("Enter Coupon Code");
		shoppingCartPO.inputToDynamicTextbox("coupon_code", couponCode);

		log.info("Click Apply Button");
		shoppingCartPO.clickToDynamicButton("Apply");

		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 4: Verify the discount generated");
		log.info("Step 4: Verify the discount generated");
		verifyEquals(shoppingCartPO.getTextDiscountInfo(), "DISCOUNT (" + couponCode + ")");
		verifyEquals(shoppingCartPO.getTextDiscountValue(), "-$25.00");

		verifyEquals(shoppingCartPO.getTextGrandTotalPrice(), "$100.00");
		log.info("========End TC_04_Verify_Discount_Coupon_works_correctly========");
		ExtentTestManager.endTest();
	}

	@Test
	public void TC_05_Verify_User_can_not_add_more_500_items() {
		ExtentTestManager.startTest("TC_05_Verify_User_can_not_add_more_500_items", "Verify that you can't add more 500 items of product");
		log.info("========Start TC_05_Verify_User_can_not_add_more_500_items========");

		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 1: Open LiveGuru99 site");
		log.info("Step 1: Open LiveGuru99 site");
		homePO.openUrl(Constants.URL_FRONTEND);

		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 2: Click on Mobile Menu and Add Iphone to Cart");
		log.info("Step 2: Click on Mobile Menu and Add Iphone to Cart");
		homePO.clickToDynamicLink("Mobile");
		productListPO = PageGeneratorManager.getProductListPage(driver);

		log.info("Verify Product List Mobile open correctly");
		verifyEquals(productListPO.getTextPageHeader(), "MOBILE");

		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 3: Add Sony Xperia to Cart");
		log.info("Step 3: Add Sony Xperia to Cart");
		productListPO.addProductToCartByName(productName);
		shoppingCartPO = PageGeneratorManager.getShoppingCartPage(driver);

		log.info("Verify Shopping Cart open correctly");
		verifyEquals(shoppingCartPO.getTextPageHeader(), "SHOPPING CART");

		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 4: Change 'QTY' value to 501 and click 'UPDATE' button");
		log.info("Step 4: Change 'QTY' value to 501 and click 'UPDATE' button");
		shoppingCartPO.inputTextToQuality("501");
		shoppingCartPO.clickToDynamicButton("Update");

		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 5: Verify the error message");
		log.info("Step 5: Verify the error message");
		verifyEquals(shoppingCartPO.getErrorMessage(), "* The maximum quantity allowed for purchase is 500.");

		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 6: Click Empty Cart Link");
		log.info("Step 6: Click Empty Cart Link");
		shoppingCartPO.clickToDynamicButton("Empty Cart");

		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 7: Verify cart is empty");
		log.info("Step 7: Verify cart is empty");
		verifyEquals(shoppingCartPO.getTextPageHeader(), "SHOPPING CART IS EMPTY");
		verifyEquals(shoppingCartPO.getCartEmptyMessage(), "You have no items in your shopping cart.");

		log.info("========End TC_05_Verify_User_can_not_add_more_500_items========");
		ExtentTestManager.endTest();
	}

	@Test
	public void TC_06_Verify_that_you_are_able_to_compare_two_products() {
		ExtentTestManager.startTest("TC_06_Verify_that_you_are_able_to_compare_two_products", "Verify that you are able to compare two products");
		log.info("========Start TC_06_Verify_that_you_are_able_to_compare_two_products========");

		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 1: Open LiveGuru99 site");
		log.info("Step 1: Open LiveGuru99 site");
		homePO.openUrl(Constants.URL_FRONTEND);

		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 2: Click on Mobile Menu and Add Iphone to Cart");
		log.info("Step 2: Click on Mobile Menu and Add Iphone to Cart");
		homePO.clickToDynamicLink("Mobile");
		productListPO = PageGeneratorManager.getProductListPage(driver);

		log.info("Verify Product List Mobile open correctly");
		verifyEquals(productListPO.getTextPageHeader(), "MOBILE");

		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 3: In mobile products list , click on ‘Add To Compare’ for 2 mobiles (Sony Xperia & Iphone)");
		log.info("Step 3: In mobile products list , click on ‘Add To Compare’ for 2 mobiles (Sony Xperia & Iphone)");

		log.info("Click Add To Compare product: " + productName1);
		productListPO.addProductToCompareByName(productName1);
		log.info("Verify show message successfully after add to compare");
		verifyEquals(productListPO.getTextSuccessMessage(), "The product " + productName1 + " has been added to comparison list.");

		log.info("Click Add To Compare product: " + productName2);
		productListPO.addProductToCompareByName(productName2);
		log.info("Verify show message successfully after add to compare");
		verifyEquals(productListPO.getTextSuccessMessage(), "The product " + productName2 + " has been added to comparison list.");

		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 4: Click Compare button");
		log.info("Click Compare button");
		productListPO.clickToDynamicButton("Compare");

		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 5: Verify the pop-up window and check that the products are reflected in it");
		log.info("Step 5: Verify the pop-up window and check that the products are reflected in it");
		compareProductPO = PageGeneratorManager.getCompareProductPage(driver);
		compareProductPO.switchToChildWindowByTitle("Products Comparison List - Magento Commerce");
		verifyEquals(compareProductPO.getTextPageHeader().toUpperCase(), "COMPARE PRODUCTS");

		log.info("Verify product name: " + productName1);
		verifyEquals(compareProductPO.getProductName(productName1).toUpperCase(), productName1.toUpperCase());
		log.info("Verify product image: " + productName1_image);
		verifyTrue(compareProductPO.getImageAttributeByProductTitle(productName1, "src").contains(productName1_image));
		log.info("Verify product price: " + productName1_price);
		verifyEquals(compareProductPO.getCostOfProductByName(productName1), productName1_price);

		log.info("Verify product name: " + productName2);
		verifyEquals(compareProductPO.getProductName(productName2).toUpperCase(), productName2.toUpperCase());
		log.info("Verify product image: " + productName2_image);
		verifyTrue(compareProductPO.getImageAttributeByProductTitle(productName2, "src").contains(productName2_image));
		log.info("Verify product price: " + productName2_price);
		verifyEquals(compareProductPO.getCostOfProductByName(productName2), productName2_price);

		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 6: Close Popup Window");
		log.info("Step 6: Close Popup Window");
		compareProductPO.clickToDynamicButton("Close Window");

		log.info("========End TC_06_Verify_that_you_are_able_to_compare_two_products========");
		ExtentTestManager.endTest();
	}

	@Test
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
		loginPO.loginSuccessfully(Constants.EMAIL_USERNAME, Constants.PASSWORD);

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
		
		log.info("Select Country");
		checkOutPO.selectDynamicDropdownList("billing:country_id","United States");
		
		log.info("Input To Phone");
		checkOutPO.inputToDynamicTextbox("billing:telephone", "0938992805");
		
		log.info("Click on ship to this address");
		checkOutPO.clickToDynamicRadioButton("billing:use_for_shipping_yes");
		
		log.info("Click To Continue button");
		checkOutPO.clickToDynamicButton("Continue");
		
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
		verifyEquals(homePO.getTextPageHeader(), "YOUR ORDER HAS BEEN RECEIVED.");
		
		
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
