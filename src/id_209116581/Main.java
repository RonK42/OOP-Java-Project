package id_209116581;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class Main implements Serializable {
	protected static Product[] storage;

	public static void main(String[] args) throws Exception {
		//final String fileName = "products_list.csv";
		final String fileName = "storage.dat";
		int userChoice = 0;
		String res = "";
		OnlineStore myStore = new OnlineStore(fileName);
		Scanner scanner = new Scanner(System.in);
		myStore.cart.loadCartFromLastExit();
		do {
			try {
				printMenu();
				userChoice = scanner.nextInt();
				int idItem;
				int quantity;

				switch (userChoice) {
				case 1:
					res = myStore.getCurrentStorage();
					printMessage(res);
					break;

				case 2:
					res = myStore.getCartContent();
					if (res.equals("")) {
						printMessage("Your cart is empty");
					} else {
						printMessage(res);
					}
					break;

				case 3:
					res = myStore.getAvailableItemsToAdd();
					printMessage(res);
					printMessage("Please enter the ID of the requested item");
					idItem = scanner.nextInt();
					myStore.checkIfExist(idItem);
					printMessage("Please enter the quintity of the requested item " + "max : "
							+ myStore.maxValidQuantity(idItem));
					quantity = scanner.nextInt();
					myStore.addProductToCart(idItem, quantity);
					printMessage("The requested item was added to your cart");
					break;

				case 4:
					printMessage("Please enter the ID the item you want to update: ");
					idItem = scanner.nextInt();
					printMessage("Please enter the quintity of the requested item " + "max : "
							+ myStore.maxValidQuantity(idItem));
					quantity = scanner.nextInt();
					myStore.updateItemQuantity(idItem, quantity);
					printMessage("Item updated..");
					break;

				case 5:
					printMessage("Please enter the ID of the requested item to remove");
					idItem = scanner.nextInt();
					myStore.removeItemFromCart(idItem);
					printMessage("The requested item was removed from your cart");
					break;
				case 6:
					printSortMenu();
					userChoice = scanner.nextInt();
					switch (userChoice) {
					case 1:
						myStore.sortByCategory(myStore.getStorage());
						res = myStore.sortToString();
						printMessage(res);
						break;
					case 2:
						myStore.sortByName(myStore.getStorage());
						res = myStore.sortToString();
						printMessage(res);
						break;
					case 3:
						myStore.sortByAvailableQuantity(myStore.getStorage());
						res = myStore.sortToString();
						printMessage(res);
						break;
					}
					break;
				case 7:
					saveStorage(myStore);
					saveCart(myStore);
					printMessage("Exit store... Thank you for shopping with us");
				default:
					printMessage("");
					break;
				}
			} catch (ReachedMaxAmountException e) {
				System.out.println(e.getMessage());
			} catch (CartProductAlreadyExistException e) {
				System.out.println(e.getMessage());
			} catch (ProductQuantityNotAvailableException e) {
				System.out.println(e.getMessage());
			} catch (InputMismatchException e) {
				System.out.println("You must add integers, stoping programm..");
				break;
			} catch (ElectrinicMoreThanThreeException e) {
				System.out.println(e.getMessage());
			} catch (OnlineStoreGeneralException e) {
				System.out.println(e.getMessage());
			}
		} while (userChoice != 7);

		scanner.close();
	}
	public static void saveStorage(OnlineStore myStore)throws Exception {
		String s = "storage.dat";
		ObjectOutputStream outFile = new ObjectOutputStream(new FileOutputStream(s));
		 outFile.writeObject(myStore.getStorage());
		 outFile.close();
	}
	public static void saveCart(OnlineStore myStore)throws Exception {
		String name = "cart.dat";
		ObjectOutputStream outFile = new ObjectOutputStream(new FileOutputStream("cart.dat"));
		 outFile.writeObject(myStore.cart.getListOfProducts());
		 outFile.close();
		
	}
	public static void printMenu() {
		printMessage("=====MENU=====\n" + "1. Show store storage and reserved inventory\n" + "2. Show your cart\n"
				+ "3. Add items to your cart\n" + "4. Update product quantity in cart\n"
				+ "5. Remove product from cart\n" + "6. Sort the storage\n7. Exit\n" + "Enter your choice (1-6):");
	}

	public static void printSortMenu() {
		printMessage(
				"Please enter what kind of sort you want? \n 1. By category\n 2. By name\n 3. By available quantity");
	}

	public static void printMessage(String message) {
		System.out.println("");
		System.out.println(message);
		System.out.println("");
	}
}
