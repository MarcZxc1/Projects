package model;

import database.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Library {
	private final DatabaseConnection db;


	public Library(DatabaseConnection db) {
		this.db = db;
	}

	public void addBook(String title, String author) {
		String sql = "INSERT INTO tblbooks (book_title, book_author, status) VALUES (?, ?, ?)";
		try (Connection conn = db.getConnection();
			 PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setString(1, title);
			pst.setString(2, author);
			pst.setString(3, BookStatus.AVAILABLE.name());
			pst.executeUpdate();
			System.out.println("Book added successfully");
		} catch (SQLException e) {
			System.out.println("Error adding book: " + e.getMessage());
		}
	}

	public void borrowBook(int id) {
		String sql = "UPDATE tblbooks SET status = ? WHERE id = ? AND status = ?";
		try (Connection conn = db.getConnection();
			 PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setString(1, BookStatus.BARROWED.name());
			pst.setInt(2, id);
			pst.setString(3, BookStatus.AVAILABLE.name());
			int rowsUpdated = pst.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("Book borrowed successfully");
			} else {
				System.out.println("Book is not available");
			}
		} catch (SQLException e) {
			System.out.println("Error borrowing book: " + e.getMessage());
		}
	}

	public void returnBook(int id) {
		String sql = "UPDATE tblbooks SET status = ? WHERE id = ?";
		try (Connection conn = db.getConnection();
			 PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setString(1, BookStatus.AVAILABLE.name());
			pst.setInt(2, id);
			int rowsUpdated = pst.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("Book returned successfully");
			} else {
				System.out.println("Book is not currently borrowed");
			}
		} catch (SQLException e) {
			System.out.println("Error returning book: " + e.getMessage());
		}
	}

	public void displayAvailableBooks() {
		String sql = "SELECT * FROM tblbooks WHERE status = ?";
		try (Connection conn = db.getConnection();
			 PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setString(1, BookStatus.AVAILABLE.name());
			ResultSet rs = pst.executeQuery();
			List<Books> books = new ArrayList<>();
			while (rs.next()) {
				books.add(new Books(rs.getInt("id"), rs.getString("book_title"), rs.getString("book_author"), BookStatus.valueOf(rs.getString("status"))));
			}
			System.out.println("Available Books:");
			for (Books book : books) {
				System.out.println(book.getId() + ": " + book.getTitle() + " by " + book.getAuthor());
			}
		} catch (SQLException e) {
			System.out.println("Error displaying available books: " + e.getMessage());
		}
	}

	public void displayBorrowedBooks() {
		String sql = "SELECT * FROM tblbooks WHERE status = ?";
		try (Connection conn = db.getConnection();
			 PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setString(1, BookStatus.BARROWED.name());
			ResultSet rs = pst.executeQuery();
			List<Books> books = new ArrayList<>();
			while (rs.next()) {
				books.add(new Books(rs.getInt("id"), rs.getString("book_title"), rs.getString("book_author"), BookStatus.valueOf(rs.getString("status"))));
			}
			if (books.isEmpty()) {
				System.out.println("No borrowed books.");
			} else {
				System.out.println("Borrowed Books:");
				for (Books book : books) {
					System.out.println(book.getId() + ": " + book.getTitle() + " by " + book.getAuthor());
				}
			}
		} catch (SQLException e) {
			System.out.println("Error displaying borrowed books: " + e.getMessage());
		}
	}
}