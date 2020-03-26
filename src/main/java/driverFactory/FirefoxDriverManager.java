package driverFactory;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FirefoxDriverManager extends DriverManager {

	@Override
	public void createDriver() {
		// TODO Auto-generated method stub
		WebDriverManager.firefoxdriver().arch64().setup();
		String rootFolder = System.getProperty("user.dir");
		FirefoxProfile profile = new FirefoxProfile();
		profile.setAcceptUntrustedCertificates(false);
		profile.setAssumeUntrustedCertificateIssuer(true);
		profile.setPreference("dom.webnotifications.enabled", false);
		profile.setPreference("browser.download.folderList", 2);
		profile.setPreference("browser.helperApps.alwayAsk.force", false);
		profile.setPreference("browser.download.manager.showWhenStarting", false);
		profile.setPreference("browser.download.downloaddir", rootFolder+"\\downloadFiles");
		profile.setPreference("browser.download.defaultFolder", rootFolder+"\\downloadFiles");
		profile.setPreference("browser.helperApps.alwayAsk.saveToDisk", "text/anytext, text/plain,text/html,application/plain");
		FirefoxOptions options = new FirefoxOptions().setProfile(profile);
		driver = new FirefoxDriver(options);
	}

	@Override
	public void quitDriver() {
		// TODO Auto-generated method stub
		
	}

}
