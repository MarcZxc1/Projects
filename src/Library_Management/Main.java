package Library_Management;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

	String dbUsername = "postgres";
	String dbPassword = "090904";
	String dbHost = "jdbc:postgresql://localhost:5432/postgres";

	// Initialize the database connection
	dbConnection db = new dbConnection(dbUsername, dbPassword, dbHost);

	userRegistration registration = new userRegistration(db);
	userLogin login = new userLogin(db);

		int pick;

		do{
			System.out.println("\n***========== Welcome to the Library Management System ==========***");
			System.out.println("1. Login");
			System.out.println("2. Register");
			System.out.println("3. Exit");

			System.out.print("Please select: ");

			pick = scan.nextInt();
			scan.nextLine();

			switch (pick){
				case 1:
					if(login.login()){
						try {
							Library library = new Library(dbUsername, dbPassword, dbHost);
							libraryMenu(scan, library);
						}catch (Exception e){
							System.out.println("Error initializing the library: "+ e.getMessage());
						}
					}
					break;

				case 2:
					registration.register();
					break;

				case 3:
					System.out.println("Please Comeback!");
					break;

					default:
						System.out.println("INVALID INPUT");
			}
		}while(pick != 3);
	}

	public static void libraryMenu(Scanner scan, Library library){
		int pick;

		do{
			System.out.println("\n***========== Library Menu ==========***");
			System.out.println("1. Library");
			System.out.println("2. Borrow Books");
			System.out.println("3. Return Books");
			System.out.println("4. Display All Available Books");
			System.out.println("5. Display All Borrowed Books");
			System.out.println("6. EXIT" );

			System.out.print("Please select: ");
			pick = scan.nextInt();
			scan.nextLine();

			try{
				switch (pick){
					case 1:
						System.out.print("Enter Book title: ");
						String title = scan.nextLine();
						System.out.print("Enter Book author: ");
						String author = scan.nextLine();
						library.addBook(title, author);
						break;

					case 2:
						System.out.print("Enter Book ID to Borrow: ");
						int borrowId = scan.nextInt();
						scan.nextLine();
						library.borrowBook(borrowId);
						break;

					case 3:
						System.out.print("Enter Book ID to Return: ");
						int returnId = scan.nextInt();
						scan.nextLine();
						library.returnBook(returnId);
						break;

					case 4:
						library.displayAvailableBooks();
						break;

					case 5:
						library.displayBorrowedBooks();
						break;

					case 6:
						System.out.println("Logging out>>>>>>:((");
						break;
				}
			}catch (Exception e){
				System.out.println("Connection error " + e.getMessage());

			}
		}while(pick !=6);

	}
}
