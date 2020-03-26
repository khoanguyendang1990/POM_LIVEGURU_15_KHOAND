package driverFactory;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ChromeDriverHeadlessManager extends DriverManager {

	@Override
	public void createDriver() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("headless");
		options.addArguments("window-size=1366x768");
		driver = new ChromeDriver(options);
	}

	@Override
	public void quitDriver() {
		// TODO Auto-generated method stub
		
	}
}
