package driverFactory;

import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ChromeDriverManager extends DriverManager {

	@Override
	public void createDriver() {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().version("74.0.3729.6").setup();
		driver = new ChromeDriver();
	}

	@Override
	public void quitDriver() {
		// TODO Auto-generated method stub
		
	}
	
}
