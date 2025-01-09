package ui;// Purpose: Register a new user in the database
import database.DatabaseConnection;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class userRegistration {
	private final DatabaseConnection db;

	public userRegistration(DatabaseConnection db) {
		this.db = db;
	}

	public void register() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter username: ");
		String username = sc.nextLine();
		System.out.print("Enter password: ");
		String password = sc.nextLine();

		// Validate input lengths
		if (username.length() > 50) {
			System.out.println("Username is too long. Maximum allowed length is 50 characters.");
			return;
		}
		if (password.length() > 50) {
			System.out.println("Password is too long. Maximum allowed length is 50 characters.");
			return;
		}

		// Hash the password
		String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

		String sql = "INSERT INTO users (username, password) VALUES (?, ?)";

		try (Connection conn = db.getConnection();
			 PreparedStatement pst = conn.prepareStatement(sql)) {

			pst.setString(1, username);
			pst.setString(2, hashedPassword);
			pst.executeUpdate();

			System.out.println("User registered successfully");

		} catch (SQLException e) {
			System.out.println("Error during registration");
			throw new RuntimeException(e);
		}
	}
}