package id_209116581;

public class CartProductNotExistException extends OnlineStoreGeneralException {
	String nameOfProduct;

	public CartProductNotExistException(String nameOfProduct) {
		super("\"" + nameOfProduct + "\" doesen't exsist.");
	}

}
