package driverFactory;

public class DriverManagerFactory {

	public static DriverManager getBrowserDriver(String browserName) {
		DriverManager driverManager;
		
		switch (browserName) {
		case "chrome_ui":
			driverManager = new ChromeDriverManager();
			break;
		case "chrome_headless":
			driverManager = new ChromeDriverHeadlessManager();
			break;
		case "firefox_ui":
			driverManager = new FirefoxDriverManager();
			break;
		default:
			driverManager = new ChromeDriverManager();
			break;
		}
		return driverManager;
	}
}
