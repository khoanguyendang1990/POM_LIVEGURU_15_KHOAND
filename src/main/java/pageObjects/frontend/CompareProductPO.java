package pageObjects.frontend;

import org.openqa.selenium.WebDriver;

import commons.ReuseFunctions;
import pageUIs.frontend.CompareProductPageUI;
import pageUIs.frontend.ProductListPageUI;

public class CompareProductPO extends ReuseFunctions {

	WebDriver driver;
	public CompareProductPO(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}
	
	public String getProductName(String titleName) {
		// TODO Auto-generated method stub
		waitForElementVisible(CompareProductPageUI.DYNAMIC_PRODUCT_NAME_A, titleName);
		return getTextElement(CompareProductPageUI.DYNAMIC_PRODUCT_NAME_A, titleName);
	}
	
	public String getImageAttributeByProductTitle(String titleName,String attributeName) {
		// TODO Auto-generated method stub
		waitForElementVisible(CompareProductPageUI.DYNAMIC_PRODUCT_IMAGE, titleName);
		return getAttributeValue(CompareProductPageUI.DYNAMIC_PRODUCT_IMAGE,attributeName, titleName);
	}
	
	public String getCostOfProductByName(String productName) {
		waitForElementVisible(CompareProductPageUI.DYNAMIC_PRICE_SPAN, productName);
		return getTextElement(CompareProductPageUI.DYNAMIC_PRICE_SPAN, productName);
	}
}
