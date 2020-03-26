package pageObjects.backend;

import org.openqa.selenium.WebDriver;

import commons.ReuseFunctions;
import pageUIs.backend.CustomerInfoPageBE_UI;
import pageUIs.backend.EditReviewPageBE_UI;
import pageUIs.backend.HomePageBE_UI;
import pageUIs.backend.ManageCustomerPageBE_UI;
import pageUIs.backend.ReviewPageBE_UI;

public class EditReviewBE_PO extends ReuseFunctions {

	WebDriver driver;
	public EditReviewBE_PO(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}

	public String getEditReviewPageHeader() {
		waitForElementVisible(EditReviewPageBE_UI.EDIT_REVIEW_PAGE_HEADER);
		return getTextElement(EditReviewPageBE_UI.EDIT_REVIEW_PAGE_HEADER);
	}
	
}
