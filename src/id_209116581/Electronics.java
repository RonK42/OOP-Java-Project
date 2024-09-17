package id_209116581;

public class Electronics extends Product {
	private String brand, model;

	// constructors
	public Electronics(String name, int quantity, String brand, String model) {
		super(name, quantity);
		this.setBrand(brand);
		this.setModel(model);
	}
	
	public Electronics(Electronics electronic) {
		super(electronic);
		this.setBrand(electronic.brand);
		this.setModel(electronic.model);
	}

	// getters & setters
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
	// other logic
	@Override
	public String toString() {
		String electronicsData = ", Brand: " + this.brand + ", Model: " + this.model;
		return super.toString() + electronicsData +"\n";
	}

}
