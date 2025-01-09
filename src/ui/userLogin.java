package ui;

import database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import org.mindrot.jbcrypt.BCrypt;

public class userLogin {

	Scanner sc = new Scanner(System.in);
	private static final int MAX_LOGIN_ATTEMPTS = 3;
	private int loginAttempts = 0;
	private final DatabaseConnection db; // db connection instance

	// Constructor to initialize the dbConnection
	public userLogin(DatabaseConnection db) {
		this.db = db;
	}

	// Method for user logins
	public boolean login() {
		if (loginAttempts >= MAX_LOGIN_ATTEMPTS) {
			System.out.println("Maximum login attempts reached");
			return false;
		}

		System.out.println("***========== Login ==========***");
		System.out.print("Enter username: ");
		String username = sc.nextLine();
		System.out.print("Enter password: ");
		String password = sc.nextLine();

		String sql = "SELECT password FROM users WHERE username = ?";

		try (Connection conn = db.getConnection();
			 PreparedStatement pst = conn.prepareStatement(sql)) {

			pst.setString(1, username);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				String storedHash = rs.getString("password");

				if (storedHash != null && storedHash.startsWith("$2")) {
					if (BCrypt.checkpw(password, storedHash)) {
						System.out.println("Login Successful");
						loginAttempts = 0;
						return true;
					}
				}
				System.out.println("Invalid password or username");
			} else {
				System.out.println("User not found");
			}
			loginAttempts++;
			return false;

		} catch (SQLException e) {
			System.out.println("Error during login");
			e.printStackTrace();
			return false;
		}
	}

	// Method for admin logins
	public boolean adminLogin() {
		if (loginAttempts >= MAX_LOGIN_ATTEMPTS) {
			System.out.println("Maximum login attempts reached");
			return false;
		}

		System.out.println("***========== Admin Login ==========***");
		System.out.print("Enter Admin Email: ");
		String email = sc.nextLine();

		String sql = "SELECT email FROM admins WHERE email = ?";

		try (Connection conn = db.getConnection();
			 PreparedStatement pst = conn.prepareStatement(sql)) {

			pst.setString(1, email);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				System.out.println("Admin login successful");
				loginAttempts = 0;
				return true;
			} else {
				System.out.println("Invalid admin email");
			}
			loginAttempts++;
			return false;

		} catch (SQLException e) {
			System.out.println("Error during admin login");
			e.printStackTrace();
			return false;
		}
	}
}