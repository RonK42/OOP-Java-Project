package id_209116581;

public class ReachedMaxAmountException extends OnlineStoreGeneralException {
	private int maxToOrder;
	private String nameOfProduct;

	public ReachedMaxAmountException(int maxToOrder, String nameOfProduct) {
		super("You are trying to order more than the maximum amount allowed due to a stock limit.\n"
				+ "The max amount of \"" + nameOfProduct + "\" is " + maxToOrder);
		this.maxToOrder = maxToOrder;
		this.nameOfProduct = nameOfProduct;
	}

}
