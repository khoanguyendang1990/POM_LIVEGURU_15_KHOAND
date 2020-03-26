package pageUIs.frontend;

public class ProductListPageUI {

	//Dynamic element
	public static final String DYNAMIC_PRICE_SPAN="//a[text()='%s']//ancestor::div[@class='product-info']//div[@class='price-box']//span[@class='price']";
	public static final String DYNAMIC_ADD_TO_CART_BUTTON="//a[text()='%s']//ancestor::div[@class='product-info']//button[@title='Add to Cart']";
	public static final String DYNAMIC_ADD_TO_COMPARE_BUTTON="//a[text()='%s']//ancestor::div[@class='product-info']//a[text()='Add to Compare']";

}
