package Library_Management;

import java.sql.*;

public class Library {

	private Connection connection;

	// Constructor to establish a connection
	public Library(String username, String password, String url) throws SQLException {
		connection = DriverManager.getConnection(url, username, password);
	}

	// Add a book to the library
	public void addBook(String title, String author) throws SQLException {
		String query = "INSERT INTO tblbooks (book_title, book_author, isborrowed) VALUES (?, ?, FALSE)";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, title);
			statement.setString(2, author);
			statement.executeUpdate();
			System.out.println("Book added: " + title + " by " + author);
		}
	}

	// Borrow a book
	public void borrowBook(int bookId) throws SQLException {
		String query = "UPDATE tblbooks SET isborrowed = TRUE WHERE id = ? AND isborrowed = FALSE";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, bookId);
			int rowsUpdated = statement.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("Book ID " + bookId + " has been borrowed.");
			} else {
				System.out.println("Book ID " + bookId + " is not available.");
			}
		}
	}

	// Return a book
	public void returnBook(int bookId) throws SQLException {
		String query = "UPDATE tblbooks SET isborrowed = FALSE WHERE id = ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, bookId);
			int rowsUpdated = statement.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("Book ID " + bookId + " has been returned.");
			} else {
				System.out.println("Book ID " + bookId + " is not currently borrowed.");
			}
		}
	}

	// Display available books
	public void displayAvailableBooks() throws SQLException {
		String query = "SELECT * FROM tblbooks WHERE isborrowed = FALSE";
		try (Statement statement = connection.createStatement();
			 ResultSet resultSet = statement.executeQuery(query)) {
			System.out.println("Available Books:");
			while (resultSet.next()) {
				System.out.println("ID: " + resultSet.getInt("id") + ", Title: " + resultSet.getString("book_title") +
						", Author: " + resultSet.getString("book_author"));
			}
		}
	}

	// Display borrowed books
	public void displayBorrowedBooks() throws SQLException {
		String query = "SELECT * FROM tblbooks WHERE isborrowed = TRUE";
		try (Statement statement = connection.createStatement();
			 ResultSet resultSet = statement.executeQuery(query)) {
			System.out.println("Borrowed Books:");
			while (resultSet.next()) {
				System.out.println("ID: " + resultSet.getInt("id") + ", Title: " + resultSet.getString("book_title") +
						", Author: " + resultSet.getString("book_author"));
			}
		}
	}
}
