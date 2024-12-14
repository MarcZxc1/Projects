package Libary_Management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class userRegistration {

	private Scanner scan;
	private dbConnection db;

	public userRegistration(dbConnection db) {
		this.scan = new Scanner(System.in);
		this.db = db;
	}

	public void register() {
		System.out.println("***========== Registration ==========***");
		System.out.print("Enter username: ");
		String username = this.scan.nextLine();
		System.out.print("Enter password: ");
		String password = this.scan.nextLine();
		String sql = "INSERT INTO users (username, password) VALUES (?, ?)";

		try {
			try (
					Connection con = this.db.getConnection();
					PreparedStatement ps = con.prepareStatement(sql);
			) {
				ps.setString(1, username);
				ps.setString(2, password);
				ps.executeUpdate();
				System.out.println("REGISTERED SUCCESSFULLY");
			}

		} catch (SQLException e) {
			System.out.println("Error during registration " + e.getMessage());
		}
	}
}

