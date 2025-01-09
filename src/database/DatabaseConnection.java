package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private String username;
	private String password;
	private String dbUrl;

	public DatabaseConnection(String username, String password, String dbUrl) {
		this.username = username;
		this.password = password;
		this.dbUrl = dbUrl;
	}

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(dbUrl, username, password);
	}
}

