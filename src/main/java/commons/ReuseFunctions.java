package commons;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotSelectableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageUIs.backend.HomePageBE_UI;
import pageUIs.commons.*;

public class ReuseFunctions {

	WebDriver driver;
	WebElement element;
	List<WebElement> elements;
	Select select;
	WebDriverWait waitExplicit;
	JavascriptExecutor jsExecutor;
	By by;
	Actions action;
	long shortTimeout = 5;
	long longTimeout = 60;
	static Logger logger = Logger.getLogger(ReuseFunctions.class.getName());

	public ReuseFunctions(WebDriver driver) {
		this.driver = driver;
		waitExplicit = new WebDriverWait(driver, longTimeout);
		jsExecutor = (JavascriptExecutor) driver;
		action = new Actions(driver);
	}

	public void openUrl(String urlValue) {
		driver.get(urlValue);
	}

	public String getPageTitle() {
		return driver.getTitle();
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	public String getPageSource() {
		return driver.getPageSource();
	}

	public void backToPage() {
		driver.navigate().back();
	}

	public void forwardToPage() {
		driver.navigate().forward();
	}

	public void refreshPage() {
		driver.navigate().refresh();
	}

	public void acceptAlert() {
		driver.switchTo().alert().accept();
	}

	public void cancelAlert() {
		driver.switchTo().alert().dismiss();
	}

	public void waitAlertPresence() {
		waitExplicit.until(ExpectedConditions.alertIsPresent());
	}

	public String getTextAlert() {
		return driver.switchTo().alert().getText();
	}

	public void sendKeyToAlert(String value) {
		driver.switchTo().alert().sendKeys(value);
	}

	public WebElement find(String locator) {
		return driver.findElement(By.xpath(locator));
	}

	public List<WebElement> finds(String locator) {
		return driver.findElements(By.xpath(locator));
	}

	public void clickToElement(String locator) {
		try {
			element = find(locator);
			element.click();
		} catch (ElementClickInterceptedException e) {
			logger.error("Exception Occurred While Clicking To Element: " + e.getMessage());
		}
	}

	public void clickToElement(String locator, String... params) {
		try {
			locator = castRestParameter(locator, params);
			element = find(locator);
			element.click();
		} catch (ElementClickInterceptedException e) {
			logger.error("Exception Occurred While Clicking To Element: " + e.getMessage());
		}
	}

	public String castRestParameter(String locator, String... params) {
		locator = String.format(locator, (Object[]) params);
		return locator;
	}

	public void sendKeyToElement(String locator, String textValue) {
			element = find(locator);
			element.clear();
			element.sendKeys(textValue);
	}

	public void sendKeyToElement(String locator,String textValue,String... values) {
		locator = castRestParameter(locator, values);
		element = find(locator);
		element.clear();
		element.sendKeys(textValue);
	}

	public void selectItemInDropdown(String locator, String valueItem) {
		try {
			element = find(locator);
			select = new Select(element);
			select.selectByVisibleText(valueItem);
		} catch (ElementNotSelectableException e) {
			logger.error("Exception Occurred While Select Item In Dropdown List To Element: " + e.getMessage());
		}
	}
	
	public void selectItemInDropdown(String locator, String valueItem,String... values) {
			locator = castRestParameter(locator, values);
			element = find(locator);
			select = new Select(element);
			select.selectByVisibleText(valueItem);
	}

	public void selectItemInCustomDropdown(String parentLocator, String allItemLocator, String expectedItem) throws InterruptedException {
		element = driver.findElement(By.xpath(parentLocator));
		sleepInSecond(1);
		element.click();
		sleepInSecond(1);
		elements = driver.findElements(By.xpath(allItemLocator));
		waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemLocator)));
		for (WebElement item : elements) {
			if (item.getText().equals(expectedItem)) {
				item.click();
				break;
			}
		}
	}

	public void selectItemInCustomDropdownJS(String parentLocator, String allItemLocator, String expectedItem) throws InterruptedException {
		element = find(parentLocator);
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].click();", element);
		sleepInSecond(1);
		waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemLocator)));
		elements = finds(allItemLocator);

		for (WebElement item : elements) {
			if (item.getText().equals(expectedItem)) {
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(1);
				item.click();
				sleepInSecond(2);
				break;
			}
		}
	}

	public void sleepInSecond(long numberInSecond) {
		try {
			Thread.sleep(numberInSecond * 1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

	}

	public String getItemInDropdown(String locator) {
		String valueItem = null;
		try {
			element = find(locator);
			select = new Select(element);
			valueItem = select.getFirstSelectedOption().getText();
		} catch (ElementNotSelectableException e) {
			logger.error("Exception Occurred While Select Item In Dropdown List To Element: " + e.getMessage());
		}
		return valueItem;
	}

	public String getAttributeValue(String locator, String attributeName) {
		element = find(locator);
		return element.getAttribute(attributeName);
	}
	
	public String getAttributeValue(String locator, String attributeName, String... params) {
		locator = castRestParameter(locator, params);
		element = find(locator);
		return element.getAttribute(attributeName);
	}

	public String getTextElement(String locator) {
		element = find(locator);
		return element.getText();
	}

	public String getTextElement(String locator, String... params) {
		locator = castRestParameter(locator, params);
		element = find(locator);
		return element.getText();
	}

	public int countElementNumber(String locator) {
		elements = finds(locator);
		return elements.size();
	}

	public void checkToCheckbox(String locator) {
		element = find(locator);
		if (element.isSelected() == false) {
			element.click();
		}
	}

	public void uncheckToCheckbox(String locator) {
		element = find(locator);
		if (element.isSelected()) {
			element.click();
		}
	}

	public boolean isElementDisplay(String locator) {
		element = find(locator);
		return element.isDisplayed();
	}
	
	public boolean isElementDisplay(String locator,String... params) {
		locator = castRestParameter(locator, params);
		element = find(locator);
		return element.isDisplayed();
	}

	public boolean isElementSelected(String locator) {
		element = find(locator);
		return element.isSelected();
	}

	public boolean isElementEnabled(String locator) {
		element = find(locator);
		return element.isEnabled();
	}

	public void switchToChildWindowByID(String parent) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parent)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}

	public void switchToChildWindowByTitle(String title) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			driver.switchTo().window(runWindow);
			String currentTitle = driver.getTitle();
			if (currentTitle.equals(title)) {
				break;
			}
		}
	}

	public boolean closeAllWindowsWithoutParent(String parentWindowTitle) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			driver.switchTo().window(runWindow);
			String currentTitle = driver.getTitle();
			if (!currentTitle.equals(parentWindowTitle)) {
				driver.switchTo().window(runWindow);
				driver.close();
			}
		}
		driver.switchTo().window(parentWindowTitle);
		if (driver.getWindowHandles().size() == 1)
			return true;
		else
			return false;
	}

	public void switchToFameOrIframe(String locator) {
		element = find(locator);
		driver.switchTo().frame(element);
	}

	public void switchToParentPage() {
		driver.switchTo().defaultContent();
	}

	public void hoverMouseToElement(String locator) {
		element = find(locator);
		action.moveToElement(element).perform();
	}
	
	public void hoverMouseToElement(String locator,String... params) {
		locator = castRestParameter(locator, params);
		element = find(locator);
		action.moveToElement(element).perform();
	}

	public void doubleClickToElement(String locator) {
		element = find(locator);
		action.doubleClick(element).perform();
	}

	public void sendKeyToElement(String locator, Keys key) {
		element = find(locator);
		action.sendKeys(element, key).perform();
	}

	public void drapAndDrop(String sourceLocator, String destinationLocator) {

		WebElement sourceElement;
		WebElement destinationElement;
		sourceElement = find(sourceLocator);
		destinationElement = find(destinationLocator);
		try {
			if (sourceElement.isDisplayed() && destinationElement.isDisplayed()) {
				Actions action = new Actions(driver);
				action.dragAndDrop(sourceElement, destinationElement).build().perform();
			} else {
				logger.error("Element was not displayed to drag");
			}
		} catch (StaleElementReferenceException e) {
			logger.error("Element with " + sourceElement + "or" + destinationElement + "is not attached to the page document " + e.getStackTrace());
		} catch (NoSuchElementException e) {
			logger.error("Element with " + sourceElement + "or" + destinationElement + "was not found in DOM " + e.getStackTrace());
		} catch (Exception e) {
			logger.error("Error occurred while performing drag and drop operation " + e.getStackTrace());
		}

	}

	public void waitForElementVisible(String locator) {
		by = By.xpath(locator);
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	public void waitForElementVisible(String locator, String... params) {
		locator = castRestParameter(locator, params);
		by = By.xpath(locator);
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	public void waitForElementPresence(String locator) {
		by = By.xpath(locator);
		waitExplicit.until(ExpectedConditions.presenceOfElementLocated(by));
	}

	public void waitForElementPresence(String locator, String... params) {
		locator = castRestParameter(locator, params);
		by = By.xpath(locator);
		waitExplicit.until(ExpectedConditions.presenceOfElementLocated(by));
	}

	public void overrideGlobalTimeout(long timeOut) {
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}

	public void waitForElementInvisible(String locator) {
		by = By.xpath(locator);
		overrideGlobalTimeout(shortTimeout);
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(by));
		overrideGlobalTimeout(longTimeout);
	}
	
	public void waitForElementInvisible(String locator, String... params) {
		locator = castRestParameter(locator, params);
		by = By.xpath(locator);
		overrideGlobalTimeout(shortTimeout);
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(by));
		overrideGlobalTimeout(longTimeout);
	}

	public boolean isControlUndisplay(String locator) {
		overrideGlobalTimeout(shortTimeout);
		List<WebElement> elements = finds(locator);
		if (elements.size() == 0) {
			overrideGlobalTimeout(longTimeout);
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			overrideGlobalTimeout(longTimeout);
			return true;
		} else {
			overrideGlobalTimeout(longTimeout);
			return false;
		}
	}

	public boolean isControlUndisplay(String locator, String... value) {
		overrideGlobalTimeout(shortTimeout);
		locator = String.format(locator, (Object[]) value);
		List<WebElement> elements = finds(locator);
		if (elements.size() == 0) {
			overrideGlobalTimeout(longTimeout);
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			overrideGlobalTimeout(longTimeout);
			return true;
		} else {
			overrideGlobalTimeout(longTimeout);
			return false;
		}
	}

	public void waitForElementClickable(String locator) {
		by = By.xpath(locator);
		waitExplicit.until(ExpectedConditions.elementToBeClickable(by));
	}
	
	public void waitForElementClickable(String locator,String... params) {
		locator = castRestParameter(locator, params);
		by = By.xpath(locator);
		waitExplicit.until(ExpectedConditions.elementToBeClickable(by));
	}

	// Browser
	public Object executeForBrowser(String javaSript) {
		return jsExecutor.executeScript(javaSript);
	}

	public boolean verifyTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		System.out.println("Text actual = " + textActual);
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void highlightElement(String locator) {
		element = driver.findElement(By.xpath(locator));
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 5px solid red; border-style: dashed;");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);

	}

	public void clickToElementByJS(String locator) {
		element = find(locator);
		jsExecutor.executeScript("arguments[0].click();", element);
	}
	
	public void clickToElementByJS(String locator,String... values) {
		locator = castRestParameter(locator, values);
		element = find(locator);
		jsExecutor.executeScript("arguments[0].click();", element);
	}

	public void scrollToElement(String locator) {
		element = driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void sendkeyToElementByJS(String locator, String value) {
		element = driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		element = driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
	}
	
	public void takeScreenshot(String testcaseName) {
		
		try {
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String currentDir = System.getProperty("user.dir");
			FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" +testcaseName+ "_" + formater.format(calendar.getTime()) + ".png"));
		} catch (IOException e) {
			System.out.println("Exception while taking screenshot: " + e.getMessage());
		}
		
	}
	//Page
	public void openMultiplePages(String pageName) {
		waitForElementVisible(CommonPageUI.DYNAMIC_LINK, pageName);
		clickToElement(CommonPageUI.DYNAMIC_LINK, pageName);
	}
	
	public void openMultiplePagesJS(String pageName) {
		waitForElementVisible(CommonPageUI.DYNAMIC_LINK, pageName);
		clickToElementByJS(CommonPageUI.DYNAMIC_LINK, pageName);
	}
	
	public void inputToDynamicTextbox(String textboxID,String textValue) {
		waitForElementVisible(CommonPageUI.DYNAMIC_TEXTBOX,textboxID);
		sendKeyToElement(CommonPageUI.DYNAMIC_TEXTBOX,textValue, textboxID);
	}
	
	public void inputToDynamicTextarea(String textareaName,String textValue) {
		waitForElementVisible(CommonPageUI.DYNAMIC_TEXTAREA,textareaName);
		sendKeyToElement(CommonPageUI.DYNAMIC_TEXTAREA,textValue, textareaName);
	}
	
	public void clickToDynamicButton(String buttonTitle) {
		waitForElementVisible(CommonPageUI.DYNAMIC_BUTTON,buttonTitle);
		clickToElement(CommonPageUI.DYNAMIC_BUTTON, buttonTitle);
	}
	
	public void clickToDynamicRadioButton(String buttonID) {
		waitForElementVisible(CommonPageUI.DYNAMIC_RADIO_BUTTON,buttonID);
		clickToElement(CommonPageUI.DYNAMIC_RADIO_BUTTON, buttonID);
	}
	
	public void clickToDynamicLink(String linkName) {
		waitForElementVisible(CommonPageUI.DYNAMIC_LINK,linkName);
		clickToElement(CommonPageUI.DYNAMIC_LINK, linkName);
	}
	
	public void clickToDynamicSpanLabel(String labelName) {
		waitForElementVisible(CommonPageUI.DYNAMIC_SPAN_LABEL,labelName);
		clickToElement(CommonPageUI.DYNAMIC_SPAN_LABEL, labelName);
	}
	
	public void selectDynamicDropdownList(String dropdownlistID,String valueItem) {
		waitForElementVisible(CommonPageUI.DYNAMIC_DROPDOWN_lIST,dropdownlistID);
		selectItemInDropdown(CommonPageUI.DYNAMIC_DROPDOWN_lIST, valueItem,dropdownlistID);
	}
	
	public String getTextPageHeader() {
		waitForElementVisible(CommonPageUI.PAGE_HEADER);
		return getTextElement(CommonPageUI.PAGE_HEADER);
	}
	
	public String getDynamicTextbox(String textboxID) {
		waitForElementVisible(CommonPageUI.DYNAMIC_TEXTBOX,textboxID);
		return getTextElement(CommonPageUI.DYNAMIC_TEXTBOX,textboxID);
	}
	
	public String getDynamicTextboxAttributeValue(String textboxID,String AttrValue) {
		waitForElementVisible(CommonPageUI.DYNAMIC_TEXTBOX,textboxID);
		return getAttributeValue(CommonPageUI.DYNAMIC_TEXTBOX,AttrValue,textboxID);
	}
	
	public String getTextSuccessMessage() {
		waitForElementVisible(CommonPageUI.SUCCESS_MESSAGE);
		return getTextElement(CommonPageUI.SUCCESS_MESSAGE);
	}
	
	public void hoverMouseToDynamicElement(String textMenu) {
		waitForElementVisible(HomePageBE_UI.DYNAMIC_MAIN_MENU_SPAN,textMenu);
		hoverMouseToElement(HomePageBE_UI.DYNAMIC_MAIN_MENU_SPAN,textMenu);
	}
	
	public void waitForLoadingMaskDisappear() {
		waitForElementInvisible(CommonPageUI.LOADING_MASK);
	}
}
