package id_209116581;

public class ProductCreator {
	protected static enum eProducts {
		Books, Electronics, Clothing
	};
	static String[]  CATAGORIES = {eProducts.Books.name(),eProducts.Clothing.name(), eProducts.Electronics.name()};

	public static Product createProductByType(String[] data) {
		String chosenCatagory = data[0];
		String name = data[1];
		int quantity = Integer.parseInt(data[2]);
		switch (eProducts.valueOf(chosenCatagory)) {
		case Books:
			String author = data[3];
			int length = Integer.parseInt(data[4]);
			return new Books(name, quantity, author, length);

		case Clothing:
			char size = data[3].charAt(0);
			String color = data[4];
			char gender = data[5].charAt(0);
			return new Clothing(name, quantity, size, color, gender);

		case Electronics:
			String brand = data[3];
			String model = data[4];

			return new Electronics(name, quantity, brand, model);
		}

		return null;
	}

	public static Product copyProduct(Product item) {
		String instanceType = item.getClass().getSimpleName();
		switch (eProducts.valueOf(instanceType)) {

		case Books:
			Books book = (Books)item;
			return new Books(book);
		case Clothing: 
			Clothing cloth = (Clothing)item;
			return new Clothing(cloth);
		case Electronics:
			Electronics electronic = (Electronics)item;
			return new Electronics(electronic);
		}
		return null;
	}

}
