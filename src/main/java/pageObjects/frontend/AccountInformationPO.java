package pageObjects.frontend;

import org.openqa.selenium.WebDriver;

import commons.ReuseFunctions;

public class AccountInformationPO extends ReuseFunctions {

	WebDriver driver;
	public AccountInformationPO(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}
}
