package pageObjects.frontend;

import org.openqa.selenium.WebDriver;

import commons.ReuseFunctions;

public class MyWishListPO extends ReuseFunctions {

	WebDriver driver;
	public MyWishListPO(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}
}
