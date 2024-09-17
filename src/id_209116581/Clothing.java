package id_209116581;

public class Clothing extends Product {
	private char size, gender;
	private String color;

	// constructors
	public Clothing(String name, int quantity, char size, String color, char gender) {
		super(name, quantity);
		this.setSize(size);
		this.setColor(color);
		this.setGender(gender);
	}
	
	public Clothing(Clothing cloth) {
		super(cloth);
		this.setSize(cloth.size);
		this.setColor(cloth.color);
		this.setGender(cloth.gender);
	}

// getters & setters
	public char getSize() {
		return size;
	}

	public void setSize(char size) {
		this.size = size;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	// other logic 
	@Override
	public String toString() {
		String clothData = " Size: " + this.size + " Gender: " + this.gender + " Color : " + this.color;
		return super.toString() + clothData +"\n";
	}

	

}
