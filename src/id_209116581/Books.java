package id_209116581;

public class Books extends Product {
	private String author;
	private int length;

	// constructors
	public Books(String name, int quantity, String author, int length) {
		super(name, quantity);
		this.setAuthor(author);
		this.setLength(length);
	}

	public Books(Books book) {
		super(book);
		this.setAuthor(book.author);
		this.setLength(book.length);
	}

	// getters & setters
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	@Override
	public String toString() {
		String bookData = ", Author: " + this.author + ", num of pages: " + this.length;
		return super.toString() + bookData + "\n";
	}

}
