package id_209116581;

public class ProductQuantityNotAvailableException extends OnlineStoreGeneralException {
	private int maxToOrder;
	private String nameOfProduct;

	public ProductQuantityNotAvailableException(int maxToOrder, String nameOfProduct) {
		super("You are trying to update the product quantity more than the maximum amount allowed due to a stock limit.\n"
				+ "The max amount of \"" + nameOfProduct + "\" is " + maxToOrder);
		this.maxToOrder = maxToOrder;
		this.nameOfProduct = nameOfProduct;
	}
}