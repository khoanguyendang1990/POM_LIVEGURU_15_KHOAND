package pageObjects.frontend;

import org.openqa.selenium.WebDriver;

import commons.ReuseFunctions;
import pageUIs.frontend.ProductDetailsPageUI;

public class ProductDetailsPO extends ReuseFunctions {

	WebDriver driver;
	public ProductDetailsPO(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}
	
	public String getProductDetailsName() {
		waitForElementVisible(ProductDetailsPageUI.PRODUCT_NAME_SPAN);
		return getTextElement(ProductDetailsPageUI.PRODUCT_NAME_SPAN);
	}
	
	public String getCostOfProduct() {
		waitForElementVisible(ProductDetailsPageUI.PRODUCT_PRICE_SPAN);
		return getTextElement(ProductDetailsPageUI.PRODUCT_PRICE_SPAN);
	}
	
	
}
