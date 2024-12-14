package Libary_Management;

import java.io.PrintStream;
import java.sql.*;

public class Library {

	private Connection connection;

	public Library(String username, String password, String url) throws SQLException {
		this.connection = DriverManager.getConnection(url, username, password);
	}

	public void addBook(String title, String author) throws SQLException {
		String query = "INSERT INTO tblbooks (book_title, book_author, isborrowed) VALUES (?, ?, FALSE)";

		try (PreparedStatement statement = this.connection.prepareStatement(query)) {
			statement.setString(1, title);
			statement.setString(2, author);
			statement.executeUpdate();
			System.out.println("Book added: " + title + " by " + author);
		}

	}

	public void borrowBook(int bookId) throws SQLException {
		String query = "UPDATE tblbooks SET isborrowed = TRUE WHERE id = ? AND isborrowed = FALSE";

		try (PreparedStatement statement = this.connection.prepareStatement(query)) {
			statement.setInt(1, bookId);
			int rowsUpdated = statement.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("Book ID " + bookId + " has been borrowed.");
			} else {
				System.out.println("Book ID " + bookId + " is not available.");
			}
		}

	}

	public void returnBook(int bookId) throws SQLException {
		String query = "UPDATE tblbooks SET isborrowed = FALSE WHERE id = ?";

		try (PreparedStatement statement = this.connection.prepareStatement(query)) {
			statement.setInt(1, bookId);
			int rowsUpdated = statement.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("Book ID " + bookId + " has been returned.");
			} else {
				System.out.println("Book ID " + bookId + " is not currently borrowed.");
			}
		}

	}

	public void displayAvailableBooks() throws SQLException {
		String query = "SELECT * FROM tblbooks WHERE isborrowed = FALSE";

		try (
				Statement statement = this.connection.createStatement();
				ResultSet resultSet = statement.executeQuery(query);
		) {
			System.out.println("Available Books:");

			while (resultSet.next()) {
				PrintStream var10000 = System.out;
				int var10001 = resultSet.getInt("id");
				var10000.println("ID: " + var10001 + ", Title: " +
						resultSet.getString("book_title") +
						", Author: " + resultSet.getString("book_author"));
			}
		}

	}

	public void displayBorrowedBooks() throws SQLException {
		String query = "SELECT * FROM tblbooks WHERE isborrowed = TRUE";

		try (
				Statement statement = this.connection.createStatement();
				ResultSet resultSet = statement.executeQuery(query);
		) {
			System.out.println("Borrowed Books:");

			while (resultSet.next()) {
				PrintStream var10000 = System.out;
				int var10001 = resultSet.getInt("id");
				var10000.println("ID: " + var10001 + ", Title: " +
						resultSet.getString("book_title") +
						", Author: " + resultSet.getString("book_author"));
			}
		}

	}

	public void removeBook(int bookId) throws SQLException {
		// Query to get the title of the book
		String getTitleQuery = "SELECT book_title FROM tblbooks WHERE id = ?";
		// Query to delete the book
		String deleteQuery = "DELETE FROM tblbooks WHERE id = ?";

		try (PreparedStatement getTitleStmt = this.connection.prepareStatement(getTitleQuery);
			 PreparedStatement deleteStmt = this.connection.prepareStatement(deleteQuery)) {

			// Set the book ID to retrieve the title
			getTitleStmt.setInt(1, bookId);
			try (ResultSet resultSet = getTitleStmt.executeQuery()) {
				if (resultSet.next()) {
					String title = resultSet.getString("book_title"); // Get the title of the book

					// Set the book ID for the DELETE query
					deleteStmt.setInt(1, bookId);
					int rowsDeleted = deleteStmt.executeUpdate();

					if (rowsDeleted > 0) {
						System.out.println("Book '" + title + "' with ID " + bookId + " has been removed.");
					}
				} else {
					System.out.println("Book ID " + bookId + " does not exist.");
				}
			}
		}
	}
}

