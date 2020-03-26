package pageObjects.backend;

import org.openqa.selenium.WebDriver;

import commons.ReuseFunctions;
import pageUIs.backend.HomePageBE_UI;

public class HomeBE_PO extends ReuseFunctions {

	WebDriver driver;
	public HomeBE_PO(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}
	
	public void clickToSubMenuByName(String subMenuName) {
		waitForElementVisible(HomePageBE_UI.DYNAMIC_SUB_MENU_SPAN,subMenuName);
		clickToElement(HomePageBE_UI.DYNAMIC_SUB_MENU_SPAN,subMenuName);
		
	}

	public void closePopup() {
		if(isElementDisplay(HomePageBE_UI.POPUP_CLOSE_SPAN)) {
			clickToElement(HomePageBE_UI.POPUP_CLOSE_SPAN);
		}
	}

	
}
