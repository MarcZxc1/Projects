package Libary_Management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class userLogin {
	Scanner sc;
	private dbConnection db;

	public userLogin(dbConnection db) {
		this.sc = new Scanner(System.in);
		this.db = db;
	}

	public boolean login() {
		System.out.println("***========== Login ==========***");
		System.out.print("Enter username: ");
		String username = this.sc.nextLine();
		System.out.print("Enter password: ");
		String password = this.sc.nextLine();
		String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

		try {
			try (
					Connection conn = this.db.getConnection();
					PreparedStatement pst = conn.prepareStatement(sql);
			) {
				pst.setString(1, username);
				pst.setString(2, password);
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					System.out.println("Login Successful");
				} else {
					System.out.println("Login Failed");
				}
			}

			return true;
		} catch (SQLException e) {
			System.out.println("Error during login");
			throw new RuntimeException(e);
		}
	}
}


