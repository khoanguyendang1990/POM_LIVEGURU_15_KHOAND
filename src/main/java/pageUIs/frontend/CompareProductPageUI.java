package pageUIs.frontend;

public class CompareProductPageUI {

	//Dynamic element
	public static final String DYNAMIC_PRODUCT_NAME_A="//table[@id='product_comparison']//tbody//td[*]/h2[@class='product-name']/a[@title='%s']";
	public static final String DYNAMIC_PRODUCT_IMAGE="//table[@id='product_comparison']//tbody//td[*]/a[@class='product-image' and @title='%s']/img";
	public static final String DYNAMIC_PRICE_SPAN="//a[text()='%s']//ancestor::td//span[@class='price']";
}
