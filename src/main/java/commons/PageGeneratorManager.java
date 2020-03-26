package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.backend.CustomerInfoBE_PO;
import pageObjects.backend.EditReviewBE_PO;
import pageObjects.backend.HomeBE_PO;
import pageObjects.backend.LoginBE_PO;
import pageObjects.backend.ManageCustomerBE_PO;
import pageObjects.backend.ReviewBE_PO;
import pageObjects.frontend.AccountInformationPO;
import pageObjects.frontend.CheckOutPO;
import pageObjects.frontend.CompareProductPO;
import pageObjects.frontend.HomePO;
import pageObjects.frontend.LoginPO;
import pageObjects.frontend.MyWishListPO;
import pageObjects.frontend.ProductDetailsPO;
import pageObjects.frontend.ProductListPO;
import pageObjects.frontend.RegisterPO;
import pageObjects.frontend.ShoppingCartPO;

public class PageGeneratorManager {

	//Front End
	public static HomePO getHomePage(WebDriver driver) {
		return new HomePO(driver);
	}
	
	public static LoginPO getLoginPage(WebDriver driver) {
		return new LoginPO(driver);
	}

	public static RegisterPO getRegisterPage(WebDriver driver) {
		return new RegisterPO(driver);
	}
	
	public static MyWishListPO getWishListPage(WebDriver driver) {
		return new MyWishListPO(driver);
	}
	
	public static AccountInformationPO getAccountInformationPage(WebDriver driver) {
		return new AccountInformationPO(driver);
	}
	
	public static ProductListPO getProductListPage(WebDriver driver) {
		return new ProductListPO(driver);
	}
	
	public static ProductDetailsPO getProductDetailsPage(WebDriver driver) {
		return new ProductDetailsPO(driver);
	}
	
	public static ShoppingCartPO getShoppingCartPage(WebDriver driver) {
		return new ShoppingCartPO(driver);
	}
	
	public static CompareProductPO getCompareProductPage(WebDriver driver) {
		return new CompareProductPO(driver);
	}
	
	public static CheckOutPO getCheckOutPage(WebDriver driver) {
		return new CheckOutPO(driver);
	}
	
	//Back End
	public static HomeBE_PO getHomeBEPage(WebDriver driver) {
		return new HomeBE_PO(driver);
	}
	
	public static LoginBE_PO getLoginBEPage(WebDriver driver) {
		return new LoginBE_PO(driver);
	}
	
	public static ManageCustomerBE_PO getManageCustomerBEPage(WebDriver driver) {
		return new ManageCustomerBE_PO(driver);
	}
	
	public static CustomerInfoBE_PO getCustomerInfoBEPage(WebDriver driver) {
		return new CustomerInfoBE_PO(driver);
	}
	
	public static ReviewBE_PO getReviewBEPage(WebDriver driver) {
		return new ReviewBE_PO(driver);
	}
	
	public static EditReviewBE_PO getEditReviewBEPage(WebDriver driver) {
		return new EditReviewBE_PO(driver);
	}
}
