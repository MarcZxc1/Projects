import database.DatabaseConfig;
import database.DatabaseConnection;
import ui.userLogin;
import ui.userRegistration;
import ui.LibraryMenu;
import model.Library;
import model.Admin;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		DatabaseConfig dbConfig = new DatabaseConfig("postgres", "090904", "jdbc:postgresql://localhost:5432/postgres");
		DatabaseConnection db = dbConfig.getDatabaseConnection();

		userRegistration registration = new userRegistration(db);
		userLogin login = new userLogin(db);

		int pick;

		do {
			System.out.println("\n***========== Welcome to the Library Management System ==========***");
			System.out.println("1. Login as Visitor");
			System.out.println("2. Login as Admin");
			System.out.println("3. Register as Visitor");
			System.out.println("4. Register as Admin");
			System.out.println("5. Exit");

			System.out.print("Please select: ");
			while (!scan.hasNextInt()) {
				System.out.println("Invalid input. Please enter a number between 1 and 5.");
				scan.next(); // Clear the invalid input
			}
			pick = scan.nextInt();
			scan.nextLine();

			switch (pick) {
				case 1:
					if (login.login()) {
						try {
							Library library = new Library(dbConfig.getDatabaseConnection());
							LibraryMenu libraryMenu = new LibraryMenu(library);
							libraryMenu.displayMenu(scan);
						} catch (Exception e) {
							System.out.println("Error initializing the library: " + e.getMessage());
						}
					}
					break;

				case 2:
					if (login.adminLogin()) {
						try {
							Admin admin = new Admin("AdminName", "admin@example.com", dbConfig.getDatabaseConnection());
							admin.displayRole();
							admin.viewAllUsers();
							// Add more admin functionalities here
						} catch (Exception e) {
							System.out.println("Error initializing the admin: " + e.getMessage());
						}
					}
					break;

				case 3:
					registration.register();
					break;

				case 4:
					System.out.print("Enter Admin Name: ");
					String adminName = scan.nextLine();
					System.out.print("Enter Admin Email: ");
					String adminEmail = scan.nextLine();
					Admin admin = new Admin(adminName, adminEmail, dbConfig.getDatabaseConnection());
					admin.registerAdmin();
					break;

				case 5:
					System.out.println("Please Comeback!");
					break;

				default:
					System.out.println("INVALID INPUT");
			}
		} while (pick != 5);
	}
}