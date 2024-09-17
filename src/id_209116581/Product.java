package id_209116581;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public abstract class Product implements Reservable,Serializable {
	final int EMPTY_STORAGE = 0;
	final SimpleDateFormat TIME_ZONE_ISRAEL_FORMAT = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	public static int ID;
	protected int id;
	protected String name, category;
	protected int quantity;
	protected int reservedQuantity;
	protected long dateNTime;

	// constructors
	public Product(String name, int quantity) {
		this.setName(name);
		this.setQuantity(quantity);
		this.setReservedQuantity(EMPTY_STORAGE);
		this.setId(ID++);
		this.setDateNTime();

	}

	public Product(Product p) {
		this.setName(p.name);
		this.setQuantity(p.quantity);
		this.setId(p.id);
		this.setDateNTime();
	}
	
	// getters and setters

	public String getName() {
		return this.name;
	}

	public String getCategory() {
		return this.getClass().getSimpleName();
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getReservedQuantity() {
		return this.reservedQuantity;
	}

	public void setReservedQuantity(int reservedQuantity) {
		this.reservedQuantity = reservedQuantity;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getDateNTime() {
		return this.dateNTime;
	}

	public void setDateNTime() {
		Date timeStamp = Calendar.getInstance().getTime();
		Long currentTime = timeStamp.getTime();
		this.dateNTime = currentTime;
	}

	public String getDateNTimeInHumanFormat() {
		return TIME_ZONE_ISRAEL_FORMAT.format(this.dateNTime);
	}

	// other logic
	public String toString() {
		String catagory = this.getClass().getSimpleName();
		return " ID: " + this.id + ", Catagory : " + catagory + ", Name: " + this.name + ", quantity: " + this.quantity
				+ ", Reserved quantity: " + this.reservedQuantity;
	}

	public String toBriefString() {
		String catagory = this.getClass().getSimpleName();
		return "ID: " + this.id + ", Catagory : " + catagory + ", Name: " + this.name + ", quantity: " + this.quantity
				+ ", last time updated: " + this.getDateNTimeInHumanFormat() + "\n";
	}

	@Override
	public void reserve(int additionalQuantity) throws ElectrinicMoreThanThreeException {
		if (this.getCategory().equals("Electronics")) {
			if (additionalQuantity > 3) {
				throw new ElectrinicMoreThanThreeException();
			}
		}
		int updatedReservedQuintity = this.reservedQuantity + additionalQuantity;
		this.setReservedQuantity(updatedReservedQuintity);

	}
}
