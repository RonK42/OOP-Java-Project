package id_209116581;
import java.util.*;

import id_209116581.ProductCreator.eProducts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class OnlineStore  implements Serializable   {

	protected Cart cart;
	protected Product[] storage;

	// constructor
	public OnlineStore(String fileName) throws Exception{
	    this.storage = this.loadProductsStorage(fileName);
		this.cart = new Cart();
	}

	// getters and setters
	public Product[] getStorage() {
		return this.storage;
	}

	public Cart getCart() {
		return this.cart;
	}

	public Product getItemInPosition(int position) throws Exception {
		if (position >= this.storage.length)
			throw new OnlineStoreGeneralException(position);

		return this.storage[position];
	}

	private Product[] loadProductsStorage(String fileName) throws Exception {
		ObjectInputStream inputFile = new ObjectInputStream(new FileInputStream(fileName));
		storage = (Product[]) inputFile.readObject();
		inputFile.close();
//		File productsList = new File(fileName);
//		Scanner scanner = new Scanner(productsList);
//
//		// skipping over the fields titles
//		scanner.nextLine();
//		Product[] storage = new Product[Product.ID];
//		while (scanner.hasNextLine()) {
//			String[] data = scanner.nextLine().split(",");
//			Product item = ProductCreator.createProductByType(data);
//			storage = Arrays.copyOf(storage, Product.ID);
//			if (item != null) {
//				storage[Product.ID - 1] = item;
//			} else {
//				// in future we will throw an exception!
//			}
//		}

	//	scanner.close();

		return storage;
	}


	// clause number 1 - get store storage and reserved inventory
	public String getCurrentStorage() {
		String res = "Storage Updates:\n";
		Product[] currentStorage = this.storage;
		for (int i = 0; i < eProducts.values().length; i++) {
			for (int j = 0; j < currentStorage.length; j++) {
				if (currentStorage[j].getClass().getSimpleName().equals(ProductCreator.CATAGORIES[i])) {
					res += currentStorage[j].toString();
				}
			}
		}

		return res;
	}

	// clause number 2 - get costumer current cart content
	public String getCartContent() {
		String currentCart = "";
		return this.cart.getCartContent(currentCart);
	}

	// clause number 3 - adding new product to costumer cart
	public String getAvailableItemsToAdd() throws Exception {
		String listOfAvailableItemsToAdd = "";
		for (int i = 0; i < this.storage.length; i++) {
			boolean isValidItem = (this.getItemInPosition(i).getQuantity()
					- (this.getItemInPosition(i).getReservedQuantity()) > 0);
			int isExistInCart = this.cart.getProductIndexInCart(i);

			if ((isExistInCart == -1) && isValidItem) {
				listOfAvailableItemsToAdd += this.getItemInPosition(i).toBriefString();
			}
		}

		return listOfAvailableItemsToAdd;
	}

	public void checkIfExist(int id) throws Exception {
		int isExistInCart = this.cart.getProductIndexInCart(id);
		if (isExistInCart != -1)
			throw new CartProductAlreadyExistException(this.getItemInPosition(id).name);
	}

	public void addProductToCart(int id, int quantity) throws Exception {
		boolean isNotRequestValid = (this.getItemInPosition(id).getQuantity()
				- (this.getItemInPosition(id).getReservedQuantity() + quantity) < 0);
		if (quantity != 0 && isNotRequestValid==false) {
			this.getItemInPosition(id).reserve(quantity);
			this.cart.addProductToCart(this.getItemInPosition(id), quantity);
			// Updating the product reserved quantity in the onlineStore

		} else {
			throw new ReachedMaxAmountException(maxValidQuantity(id), this.getItemInPosition(id).name);
		}
	}

	public int maxValidQuantity(int id) throws Exception {
		int max = (this.getItemInPosition(id).getQuantity() - this.getItemInPosition(id).getReservedQuantity());
		return max;
	}

	// clause number 4 - Update item quantity in cart
	public void updateItemQuantity(int idItem, int newQuintity) throws Exception {
		int maxQuintity = maxValidQuantity(idItem);
		if (newQuintity == 0) {
			this.removeItemFromCart(idItem);
		} else if (maxQuintity < newQuintity) {
			throw new ProductQuantityNotAvailableException(maxQuintity, this.getItemInPosition(idItem).name);
		}

		else {
			int oldReservedQuantityOfItem = this.getItemInPosition(idItem).getReservedQuantity();
			int updatedReservedQuantityOfItem = (newQuintity + oldReservedQuantityOfItem);
			this.getItemInPosition(idItem).reserve(updatedReservedQuantityOfItem);
			this.cart.updateProductReservedQuantity(idItem, newQuintity);

		}
	}

	// clause number 5 - remove item from costumer cart
	public void removeItemFromCart(int idItem) throws Exception {
		this.getCart().removeItemFromCart(idItem);
		this.getItemInPosition(idItem).setReservedQuantity(0);
	}

	// clause number 6 - Sort the product list
	public String sortToString() {
		String res = "Storage Updates:\n";
		Product[] currentStorage = this.storage;
		for (int i = 0; i < currentStorage.length; i++) {
			res += currentStorage[i].toString();

		}
		return res;

	}

	public Product[] sortByCategory(Product[] p) {
		Arrays.sort(p, new CompareProductByCategory());
		return p;
	}

	public Product[] sortByName(Product[] p) {
		Arrays.sort(p, new CompareProductByName());
		return p;
	}

	public Product[] sortByAvailableQuantity(Product[] p) {
		Arrays.sort(p, new CompareProductByQuantity());
		return p;
	}
}
