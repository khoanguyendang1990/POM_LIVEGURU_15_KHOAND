package pageObjects.frontend;

import org.openqa.selenium.WebDriver;

import commons.ReuseFunctions;
import pageUIs.frontend.HomePageUI;

public class HomePO extends ReuseFunctions {

	WebDriver driver;
	public HomePO(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}
	public String getOrderNumber() {
		waitForElementVisible(HomePageUI.ORDER_ID_A);
		return getTextElement(HomePageUI.ORDER_ID_A);
	}
	
	public String getOrderSuccessMessage() {
		waitForElementVisible(HomePageUI.ORDER_SUCCESS_MESSAGE);
		return getTextElement(HomePageUI.ORDER_SUCCESS_MESSAGE);
	}
}
