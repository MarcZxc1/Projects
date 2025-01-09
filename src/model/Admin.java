package model;

import database.DatabaseConnection;
import java.sql.*;

public class Admin extends Person {
	private final DatabaseConnection db;

	public Admin(String name, String email, DatabaseConnection db) {
		super(name, email);
		this.db = db;
	}

	@Override
	public void displayRole() {
		System.out.println("Role: Admin");
	}

	public void registerAdmin() {
		String sql = "INSERT INTO admins (name, email) VALUES (?, ?)";
		try (Connection conn = db.getConnection();
			 PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setString(1, getName());
			pst.setString(2, getEmail());
			pst.executeUpdate();
			System.out.println("Admin registered successfully");
		} catch (SQLException e) {
			System.out.println("Error registering admin: " + e.getMessage());
		}
	}

	public void removeUser(int userId) {
		String sql = "DELETE FROM users WHERE id = ?";
		try (Connection conn = db.getConnection();
			 PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setInt(1, userId);
			int rowsDeleted = pst.executeUpdate();
			if (rowsDeleted > 0) {
				System.out.println("User removed successfully");
			} else {
				System.out.println("User not found");
			}
		} catch (SQLException e) {
			System.out.println("Error removing user: " + e.getMessage());
		}
	}


	public void viewAllUsers() {
		String sql = "SELECT id, username, password FROM users";
		try (Connection conn = db.getConnection();
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(sql)) {
			System.out.println("All Users:");
			while (rs.next()) {
				System.out.println("ID: " + rs.getInt("id") + ", Username: " + rs.getString("username") + ", Password: " + rs.getString("password"));
			}
		} catch (SQLException e) {
			System.out.println("Error viewing users: " + e.getMessage());
		}
	}
}