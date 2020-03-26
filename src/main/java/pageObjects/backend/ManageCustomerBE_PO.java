package pageObjects.backend;

import org.openqa.selenium.WebDriver;

import commons.ReuseFunctions;
import pageUIs.backend.HomePageBE_UI;
import pageUIs.backend.ManageCustomerPageBE_UI;
import pageUIs.backend.ReviewPageBE_UI;
import pageUIs.commons.CommonPageUI;

public class ManageCustomerBE_PO extends ReuseFunctions {

	WebDriver driver;
	public ManageCustomerBE_PO(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}

	public boolean isManageCustomerPageDisplayed() {
		return isElementDisplay(ManageCustomerPageBE_UI.MANAGE_CUSTOMER_HEADER);
	}
	
	public void editUserByEmail(String email) {
		waitForElementVisible(ManageCustomerPageBE_UI.DYNAMIC_CUSTOMER_GRID_EDIT_A, email);
		clickToElement(ManageCustomerPageBE_UI.DYNAMIC_CUSTOMER_GRID_EDIT_A, email);
	}

	public boolean isReviewNotDisplayInGrid(String email) {
		return isControlUndisplay(ManageCustomerPageBE_UI.DYNAMIC_CUSTOMER_GRID_EDIT_A, email);
	}

}
