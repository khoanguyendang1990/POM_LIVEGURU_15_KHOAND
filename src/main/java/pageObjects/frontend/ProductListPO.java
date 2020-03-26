package pageObjects.frontend;

import org.openqa.selenium.WebDriver;

import commons.ReuseFunctions;
import pageUIs.frontend.ProductListPageUI;

public class ProductListPO extends ReuseFunctions {

	WebDriver driver;
	public ProductListPO(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}
	public String getCostOfProductByName(String productName) {
		// TODO Auto-generated method stub
		waitForElementVisible(ProductListPageUI.DYNAMIC_PRICE_SPAN, productName);
		return getTextElement(ProductListPageUI.DYNAMIC_PRICE_SPAN, productName);
	}
	public void addProductToCartByName(String productName) {
		waitForElementVisible(ProductListPageUI.DYNAMIC_ADD_TO_CART_BUTTON, productName);
		clickToElement(ProductListPageUI.DYNAMIC_ADD_TO_CART_BUTTON, productName);
	}
	public void addProductToCompareByName(String productName) {
		waitForElementVisible(ProductListPageUI.DYNAMIC_ADD_TO_COMPARE_BUTTON, productName);
		clickToElement(ProductListPageUI.DYNAMIC_ADD_TO_COMPARE_BUTTON, productName);
		
	}
}
