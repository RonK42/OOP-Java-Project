package id_209116581;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Arrays;

public class Cart implements Serializable {
	protected Product[] listOfProductsInCart;

	// constructors
	public Cart() {
		this.listOfProductsInCart = new Product[0];
	}

	// getters & setters
	public Product[] getListOfProducts() {
		return this.listOfProductsInCart;
	}

	public void setListOfProducts(Product[] p) {
		this.listOfProductsInCart = p;
	}

	// other logic
	public Product getItemInPosition(int position) {
		return this.listOfProductsInCart[position];
	}

	public void setItemInPosition(int position, Product p) {
		this.listOfProductsInCart[position] = p;
	}

	public String getCartContent(String res) {
		Product[] currentCart = this.listOfProductsInCart;
		for (int i = 0; i < currentCart.length; i++) {
			res += currentCart[i].toBriefString();
		}
		return res;
	}
	public void loadCartFromLastExit() throws Exception {
		ObjectInputStream inputFile = new ObjectInputStream(new FileInputStream("cart.dat"));
		Product[] some= (Product[]) inputFile.readObject();
		setListOfProducts(some);
		inputFile.close();
	}
	
	private void addProduct(Product item, int quantity) {
		int currentCartSize = this.listOfProductsInCart.length;
		Product copyP = ProductCreator.copyProduct(item);
		copyP.setQuantity(quantity);
		currentCartSize++;
		Product[] newArr = Arrays.copyOf(this.listOfProductsInCart, currentCartSize);
		this.setListOfProducts(newArr);
		this.setItemInPosition(currentCartSize - 1, copyP);
	}

	public int addProductToCart(Product item, int quantity) {
		int index = getProductIndexInCart(item.getId());
		if (index == -1) {
			this.addProduct(item, quantity);
			return quantity;
		} else {
			return updateProductReservedQuantity(item.getId(), quantity);
		}
	}

	public int getProductIndexInCart(int id) {
		Product[] currentCart = this.listOfProductsInCart;
		for (int i = 0; i < currentCart.length; i++) {
			if (currentCart[i].id == id)
				return i;
		}
		return -1;
	}

	public int updateProductReservedQuantity(int id, int newQuantity) {
		int oldQuantity = 0;
		for (int i = 0; i < this.listOfProductsInCart.length; i++) {
			if (this.getItemInPosition(i).getId() == id) {
				oldQuantity = this.getItemInPosition(i).getQuantity();
				this.getItemInPosition(i).setQuantity(newQuantity);
				this.getItemInPosition(i).setDateNTime();
				return (newQuantity - oldQuantity);
			}
		}
		return oldQuantity;
	}

	public int removeItemFromCart(int idItem) {
		Product[] updatedCart = new Product[this.listOfProductsInCart.length - 1];
		int updatedReservedQuantity = this.removeItem(updatedCart, idItem);
		this.setListOfProducts(updatedCart);
		return updatedReservedQuantity;
	}

	private int removeItem(Product[] updatedCart, int idItem) {
		int reservedQuantity = 0;
		int indexCounter = 0;
		for (int i = 0; i < (this.listOfProductsInCart.length); i++) {
			boolean shouldRemoveItem = this.getItemInPosition(i).getId() == idItem;
			if (shouldRemoveItem) {
				reservedQuantity = this.getItemInPosition(i).getQuantity();
			} else {
				updatedCart[indexCounter] = this.getItemInPosition(i);
				indexCounter++;
			}
		}

		return reservedQuantity;
	}
}
