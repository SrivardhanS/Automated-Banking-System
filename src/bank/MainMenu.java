package bank; // Define the package name

import java.util.InputMismatchException; // Import for handling input mismatch exceptions
import java.util.Scanner; // Import for taking user input

public class MainMenu { // Define the MainMenu class
    public void display(Scanner scanner) { // Method to display the main menu
        while (true) { // Infinite loop for continuous menu display
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Check Balance");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            try { // Try block to handle input
                int choice = scanner.nextInt(); // Read user input

                switch (choice) { // Switch based on user choice
                    case 1:
                        new Account().createAccount(scanner); // Call createAccount method
                        break;
                    case 2:
                        new Deposit().depositMoney(scanner); // Call depositMoney method
                        break;
                    case 3:
                        new Withdraw().withdrawMoney(scanner); // Call withdrawMoney method
                        break;
                    case 4:
                        new Balance().checkBalance(scanner); // Call checkBalance method
                        break;
                    case 5:
                        System.out.println("Exiting..."); // Exit message
                        System.exit(0); // Exit the program
                        break;
                    default:
                        System.out.println("Invalid choice! Please try again."); // Handle invalid choice
                }
            } catch (InputMismatchException e) { // Catch block for handling input mismatch
                System.out.println("Invalid input! Please enter a number."); // Error message for invalid input
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }
}
