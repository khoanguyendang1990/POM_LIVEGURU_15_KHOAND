package pageUIs.commons;

public class CommonPageUI {

	//Header
	public static final String PAGE_HEADER="//h1";
	
	//
	public static final String SUCCESS_MESSAGE="//li[@class='success-msg']//span";
	public static final String LOADING_MASK="//div[@id='loading-mask']";
	
	// Dynamic Element Component
	public static final String DYNAMIC_LINK="//a[text()='%s']";
	public static final String DYNAMIC_TEXTBOX="//input[@id='%s']";
	public static final String DYNAMIC_RADIO_BUTTON="//input[@id='%s']";
	public static final String DYNAMIC_SPAN_LABEL="//span[@class='label'][text()='%s']";
	
	public static final String DYNAMIC_TEXTAREA="//textarea[@name='%s']";
	public static final String DYNAMIC_BUTTON="//button[@title='%s']";
	public static final String DYNAMIC_PAGE_HEADING="//h1";
	
	public static final String DYNAMIC_DROPDOWN_lIST="//select[@id='%s']";
}
