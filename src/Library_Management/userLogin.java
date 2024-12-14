package Library_Management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class userLogin{
	Scanner sc = new Scanner(System.in);

	private dbConnection db; // db connection instance

	// Constructor to initialize the dbConnection
	public userLogin(dbConnection db){
		this.db = db;
	}
	//method for userlogins
	public boolean login(){
		System.out.println("***========== Login ==========***");
		System.out.print("Enter username: ");
		String username = sc.nextLine();
		System.out.print("Enter password: ");
		String password = sc.nextLine();
		// check the db if there is username and password record
		String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

		try(Connection conn = db.getConnection();
		PreparedStatement pst = conn.prepareStatement(sql)){

			pst.setString(1, username);
			pst.setString(2, password);

			// Execute the query and get results
			ResultSet rs = pst.executeQuery();

			if(rs.next()){
				//if there is match then login successfully
				System.out.println("Login Successful");
			}else{
				System.out.println("Login Failed");
			}

		} catch (SQLException e) {

			System.out.println("Error during login");
			throw new RuntimeException(e);
		}
		return true;
	}
}
