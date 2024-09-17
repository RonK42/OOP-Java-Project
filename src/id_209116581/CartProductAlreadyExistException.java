package id_209116581;

public class CartProductAlreadyExistException extends OnlineStoreGeneralException {
	String nameOfProduct;

	public CartProductAlreadyExistException(String nameOfProduct) {
		super("The product \"" + nameOfProduct + "\" is already exist in your cart.");
		this.nameOfProduct = nameOfProduct;
	}
}
