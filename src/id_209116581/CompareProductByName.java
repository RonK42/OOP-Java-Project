package id_209116581;

import java.util.Comparator;

public class CompareProductByName implements Comparator<Product> {

	@Override
	public int compare(Product p1, Product p2) {

		return p1.getName().toLowerCase().compareTo(p2.getName().toLowerCase());
	}

}
