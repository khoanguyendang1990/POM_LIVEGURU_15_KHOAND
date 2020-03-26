package pageObjects.frontend;

import org.openqa.selenium.WebDriver;

import commons.ReuseFunctions;

public class RegisterPO extends ReuseFunctions {

	WebDriver driver;
	public RegisterPO(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}
}
