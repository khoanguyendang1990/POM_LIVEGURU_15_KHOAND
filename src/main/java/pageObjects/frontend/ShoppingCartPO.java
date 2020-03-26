package pageObjects.frontend;

import org.openqa.selenium.WebDriver;

import commons.ReuseFunctions;
import pageUIs.frontend.ShoppingCartPageUI;

public class ShoppingCartPO extends ReuseFunctions {

	WebDriver driver;
	public ShoppingCartPO(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}
	public String getTextDiscountInfo() {
		waitForElementVisible(ShoppingCartPageUI.DISCOUNT_INFO_TD);
		return getTextElement(ShoppingCartPageUI.DISCOUNT_INFO_TD);
	}
	public String getTextDiscountValue() {
		waitForElementVisible(ShoppingCartPageUI.DISCOUNT_VALUE_TD);
		return getTextElement(ShoppingCartPageUI.DISCOUNT_VALUE_TD);
	}
	
	public String getTextShippingInfo() {
		waitForElementVisible(ShoppingCartPageUI.SHIPPING_HANDLING_INFO_TD);
		return getTextElement(ShoppingCartPageUI.SHIPPING_HANDLING_INFO_TD);
	}
	public String getTextShippingValue() {
		waitForElementVisible(ShoppingCartPageUI.SHIPPING_HANDLING_VALUE_TD);
		return getTextElement(ShoppingCartPageUI.SHIPPING_HANDLING_VALUE_TD);
	}
	
	public String getTextGrandTotalPrice() {
		waitForElementVisible(ShoppingCartPageUI.GRAND_TOTAL_PRICE_SPAN);
		return getTextElement(ShoppingCartPageUI.GRAND_TOTAL_PRICE_SPAN);
	}
	public void inputTextToQuality(String qualityValue) {
		waitForElementVisible(ShoppingCartPageUI.QUALITY_TEXTBOX);
		sendKeyToElement(ShoppingCartPageUI.QUALITY_TEXTBOX, qualityValue);
	}
	public String getErrorMessage() {
		waitForElementVisible(ShoppingCartPageUI.ERROR_MESSAGE);
		return getTextElement(ShoppingCartPageUI.ERROR_MESSAGE);
	}
	public String getCartEmptyMessage() {
		waitForElementVisible(ShoppingCartPageUI.CART_EMPTY_MESSAGE);
		return getTextElement(ShoppingCartPageUI.CART_EMPTY_MESSAGE);
		
	}
	public String getFlatRateShippingCost() {
		waitForElementVisible(ShoppingCartPageUI.FLAT_RATES_SHIPPING_PRICE_SPAN);
		return getTextElement(ShoppingCartPageUI.FLAT_RATES_SHIPPING_PRICE_SPAN);
	}
	
}
