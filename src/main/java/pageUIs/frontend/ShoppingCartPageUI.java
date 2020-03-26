package pageUIs.frontend;

public class ShoppingCartPageUI {

	public static final String QUALITY_TEXTBOX="//input[@title='Qty']";
	public static final String ERROR_MESSAGE="//p[@class='item-msg error']";
	public static final String CART_EMPTY_MESSAGE="//div[@class='cart-empty']/P";
	
	
	public static final String DISCOUNT_INFO_TD="//table[@id='shopping-cart-totals-table']//td[contains(text(),'Discount')]";
	public static final String DISCOUNT_VALUE_TD="//table[@id='shopping-cart-totals-table']//td[contains(text(),'Discount')]//following-sibling::td/span[@class='price']";
	public static final String SHIPPING_HANDLING_INFO_TD="//table[@id='shopping-cart-totals-table']//td[contains(text(),'Shipping & Handling')]";
	public static final String SHIPPING_HANDLING_VALUE_TD="//table[@id='shopping-cart-totals-table']//td[contains(text(),'Shipping & Handling')]//following-sibling::td/span[@class='price']";
	public static final String GRAND_TOTAL_PRICE_SPAN="//table[@id='shopping-cart-totals-table']//tfoot//strong[text()='Grand Total']//ancestor::tr//span[@class='price']";
	public static final String FLAT_RATES_SHIPPING_PRICE_SPAN="//form[@id='co-shipping-method-form']//span[@class='price']";
	

}
