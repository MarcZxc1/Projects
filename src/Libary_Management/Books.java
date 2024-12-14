package Libary_Management;

public class Books {

	private int id;
	private String title;
	private String author;
	private boolean isBarrowed;

	public Books(int id, String title, String author, boolean isBarrowed) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.isBarrowed = isBarrowed;
	}

	public int getId() {return this.id;}

	public String getTitle() {
		return this.title;
	}

	public String getAuthor() {
		return this.author;
	}

	public boolean isAvailable() {return this.isBarrowed;}

	public void barrow() {
		this.isBarrowed = true;
	}

	public void returnBook() {
		this.isBarrowed = false;
	}

	public String toString() {
		return "ID: " + this.id + " Title: " + this.title +
				" Author: " + this.author + " isAvailable: "
				+ this.isBarrowed;
	}
}


