package database;

public class DatabaseConfig {
	private final String dbUsername;
	private final String dbPassword;
	private final String dbHost;

	public DatabaseConfig(String dbUsername, String dbPassword, String dbHost) {
		this.dbUsername = dbUsername;
		this.dbPassword = dbPassword;
		this.dbHost = dbHost;
	}

	public DatabaseConnection getDatabaseConnection() {
		return new DatabaseConnection(dbUsername, dbPassword, dbHost);
	}
}