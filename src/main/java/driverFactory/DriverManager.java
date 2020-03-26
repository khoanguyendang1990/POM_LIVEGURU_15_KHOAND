package driverFactory;

import org.openqa.selenium.WebDriver;

public abstract class DriverManager {

	protected WebDriver driver;

	public abstract void createDriver();

	public abstract void quitDriver();

	public WebDriver getDriver() {
		if (driver == null) {
			createDriver();
		}
		return driver;
	}
}
