package id_209116581;

import java.util.Comparator;

public class CompareProductByQuantity implements Comparator<Product> {

	@Override
	public int compare(Product p1, Product p2) {
		return Integer.compare(p1.getQuantity(), p2.getQuantity());

	}
}
