package Library_Management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {

	private String username;
	private String password;
	private String url;

	dbConnection(String username, String password, String url) {
		this.username = username;
		this.password = password;
		this.url = url;
	}

	public Connection getConnection()throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}

}
