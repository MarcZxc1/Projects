package ui;

import model.Library;
import java.util.Scanner;

public class LibraryMenu{

	private final Library library;

	public LibraryMenu(Library library) {
		this.library = library;
	}

	public void displayMenu(Scanner scan) {
		int pick;

		do {
			System.out.println("\n***========== Library Menu ==========***");
			System.out.println("1. Add Books");
			System.out.println("2. Borrow Books");
			System.out.println("3. Return Books");
			System.out.println("4. Display Available Books");
			System.out.println("5. Display All Borrowed Books");
			System.out.println("6. EXIT");

			System.out.print("Please select: ");
			pick = scan.nextInt();
			scan.nextLine();

			try {
				switch (pick) {
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

					default:
						System.out.println("INVALID INPUT");
				}
			} catch (Exception e) {
				System.out.println("Connection error " + e.getMessage());
			}
		} while (pick != 6);
	}
}