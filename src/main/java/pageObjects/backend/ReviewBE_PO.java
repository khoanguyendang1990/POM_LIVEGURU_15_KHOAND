package pageObjects.backend;

import org.openqa.selenium.WebDriver;

import commons.ReuseFunctions;
import pageUIs.backend.HomePageBE_UI;
import pageUIs.backend.ManageCustomerPageBE_UI;
import pageUIs.backend.ReviewPageBE_UI;
import pageUIs.commons.CommonPageUI;

public class ReviewBE_PO extends ReuseFunctions {

	WebDriver driver;
	public ReviewBE_PO(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}

	public String getReviewPageHeader() {
		waitForElementVisible(ReviewPageBE_UI.REVIEW_PAGE_HEADER);
		return getTextElement(ReviewPageBE_UI.REVIEW_PAGE_HEADER);
	}

	public void editReviewByTitleSummary(String titleSummary) {
		waitForElementVisible(ReviewPageBE_UI.DYNAMIC_REVIEW_GRID_EDIT_A, titleSummary);
		clickToElement(ReviewPageBE_UI.DYNAMIC_REVIEW_GRID_EDIT_A, titleSummary);
		
	}
	
	public boolean isReviewNotDisplayInGrid(String titleSummary) {
		return isControlUndisplay(ReviewPageBE_UI.DYNAMIC_REVIEW_GRID_EDIT_A, titleSummary);
	}
}
