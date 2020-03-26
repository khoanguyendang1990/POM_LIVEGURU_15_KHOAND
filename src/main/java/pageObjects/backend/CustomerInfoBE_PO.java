package pageObjects.backend;

import org.openqa.selenium.WebDriver;

import commons.ReuseFunctions;
import pageUIs.backend.CustomerInfoPageBE_UI;
import pageUIs.backend.HomePageBE_UI;
import pageUIs.backend.ManageCustomerPageBE_UI;

public class CustomerInfoBE_PO extends ReuseFunctions {

	WebDriver driver;
	public CustomerInfoBE_PO(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}

	public boolean isCustomerInfoPageDisplayed() {
		return isElementDisplay(CustomerInfoPageBE_UI.CUSTOMER_INFO_PAGE_HEADER);
	}
	
}
