package Library_Management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class userRegistration{

	private Scanner scan = new Scanner(System.in);
	private dbConnection db; //db connection instance

	// Constructor to initialize the dbConnection
	public userRegistration(dbConnection db){
		this.db = db;
	}

	// Method to register a new user
	public void register(){
		System.out.println("***========== Registration ==========***");
		System.out.print("Enter username: ");
		String username = scan.nextLine();
		System.out.print("Enter password: ");
		String password = scan.nextLine();

		// SQL query to insert new user credentials into the database
		String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
		try (Connection con = db.getConnection();

		PreparedStatement ps = con.prepareStatement(sql)){
			// Set parameters for the prepared statement
			ps.setString(1, username);
			ps.setString(2, password);

			// Execute the query to insert data
			ps.executeUpdate();
			System.out.println("REGISTERED SUCCESSFULLY");

		} catch (SQLException e) {
			//sql exception
			System.out.println("Error during registration " +
			e.getMessage());
			return;

		}
	}



}
