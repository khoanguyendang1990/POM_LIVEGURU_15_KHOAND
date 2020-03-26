package pageObjects.frontend;

import org.openqa.selenium.WebDriver;

import commons.ReuseFunctions;
import commons.PageGeneratorManager;
import pageUIs.commons.CommonPageUI;
import pageUIs.frontend.LoginPageUI;

public class LoginPO extends ReuseFunctions {

	WebDriver driver;

	public LoginPO(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void inputTextToEmail(String email) {
		waitForElementVisible(CommonPageUI.DYNAMIC_TEXTBOX,"email");
		sendKeyToElement(CommonPageUI.DYNAMIC_TEXTBOX, email,"email");
	}

	public void inpurtTextToPassword(String password) {
		waitForElementVisible(CommonPageUI.DYNAMIC_TEXTBOX,"pass");
		sendKeyToElement(CommonPageUI.DYNAMIC_TEXTBOX, password,"pass");
	}

	public void clickOnLogin() {	
		waitForElementClickable(CommonPageUI.DYNAMIC_BUTTON,"Login");
		clickToElement(CommonPageUI.DYNAMIC_BUTTON,"Login");
	}

	public HomePO loginSuccessfully(String userID, String password) {
		inputTextToEmail(userID);
		inpurtTextToPassword(password);
		clickOnLogin();
		return PageGeneratorManager.getHomePage(driver);
	}
}
