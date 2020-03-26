package pageObjects.backend;

import org.openqa.selenium.WebDriver;

import commons.ReuseFunctions;
import commons.PageGeneratorManager;
import pageUIs.backend.LoginPageBE_UI;
import pageUIs.commons.CommonPageUI;
import pageUIs.frontend.LoginPageUI;

public class LoginBE_PO extends ReuseFunctions {

	WebDriver driver;

	public LoginBE_PO(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void inputTextToUserName(String userName) {
		waitForElementVisible(CommonPageUI.DYNAMIC_TEXTBOX,"username");
		sendKeyToElement(CommonPageUI.DYNAMIC_TEXTBOX, userName,"username");
	}

	public void inpurtTextToPassword(String password) {
		waitForElementVisible(CommonPageUI.DYNAMIC_TEXTBOX,"login");
		sendKeyToElement(CommonPageUI.DYNAMIC_TEXTBOX, password,"login");
	}

	public void clickOnLogin() {	
		waitForElementClickable(LoginPageBE_UI.LOGIN_BUTTON);
		clickToElement(LoginPageBE_UI.LOGIN_BUTTON);
	}

	public HomeBE_PO loginSuccessfully(String userID, String password) {
		inputTextToUserName(userID);
		inpurtTextToPassword(password);
		clickOnLogin();
		return PageGeneratorManager.getHomeBEPage(driver);
	}
}
