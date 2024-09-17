package id_209116581;

public class OnlineStoreGeneralException extends Exception {
	int ID;

	public OnlineStoreGeneralException(String msg) {
		super(msg);
	}

	public OnlineStoreGeneralException(int ID) {
		super("Invaid ID num, the ID " + ID + " Doesen't exict.");
		this.ID = ID;
	}

	// NEED MORE EXCEPTIONS
	public OnlineStoreGeneralException() {
		super("General Online Store Error...");
	}

}
