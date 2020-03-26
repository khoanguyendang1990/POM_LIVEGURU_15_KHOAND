package pageObjects.frontend;

import org.openqa.selenium.WebDriver;

import commons.ReuseFunctions;
import pageUIs.frontend.CheckOutPageUI;

public class CheckOutPO extends ReuseFunctions {

	WebDriver driver;
	public CheckOutPO(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}
	public void clickToContrinueButtonOnShippingMethod() {
		waitForElementClickable(CheckOutPageUI.SHIPPING_METHOD_CONTINUE_BUTTON);
		clickToElement(CheckOutPageUI.SHIPPING_METHOD_CONTINUE_BUTTON);
	}
	
	public void clickToContrinueButtonOnPaymentInfo() {
		waitForElementClickable(CheckOutPageUI.PAYMENT_INFORMATION_CONTINUE_BUTTON);
		clickToElement(CheckOutPageUI.PAYMENT_INFORMATION_CONTINUE_BUTTON);
	}
	public void clickToContrinueButtonOnBillingMethod() {
		waitForElementClickable(CheckOutPageUI.BILLING_METHOD_CONTINUE_BUTTON);
		clickToElement(CheckOutPageUI.BILLING_METHOD_CONTINUE_BUTTON);
		
	}
}
