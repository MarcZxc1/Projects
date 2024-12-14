package Library_Management;

public class Book {

	private int id;
	private String title;
	private String author;
	private boolean isBarrowed;

	public Book(int id, String title, String author, boolean isBarrowed) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.isBarrowed = isBarrowed;
	}

	public int getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getAuthor() {
		return author;
	}
	public boolean isAvailable() {
		return isBarrowed;
	}
	public void barrow(){
		this.isBarrowed = true;
	}
	public void returnBook(){
		this.isBarrowed = false;
	}

	@Override
	public String toString() {
		return "ID: " + id + " Title: " + title + " Author: " + author + " isAvailable: " + isBarrowed;
	}


}


