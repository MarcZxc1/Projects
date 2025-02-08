package com.dev.marc.ims.dbConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databaseConnection {

	private static final String URL = "jdbc:postgresql://localhost:5432/Inventory+Management+System";
	private static final String USER = "postgres";
	private static final String PASSWORD = "090904";

	public static Connection getConnection() {
		try {
			Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("yown tangina!");
			return connection;
		} catch (SQLException e) {
			System.err.println("tangina talaga: " + e.getMessage());
			return null;
		}
	}
}
