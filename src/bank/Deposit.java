package bank; // Declares the package name for this class

import java.sql.Connection; // Imports the Connection class for establishing database connections
import java.sql.PreparedStatement; // Imports the PreparedStatement class for executing parameterized SQL queries
import java.sql.SQLException; // Imports the SQLException class for handling SQL-related exceptions
import java.util.InputMismatchException; // Imports InputMismatchException class for handling invalid input
import java.util.Scanner; // Imports the Scanner class for taking user input

public class Deposit { // Declares the Deposit class
    // Method to deposit money into an account
    public void depositMoney(Scanner scanner) {
        int accountID = -1; // Initialize accountID to an invalid value
        double amount = -1; // Initialize amount to an invalid value

        // Loop to ensure valid input for account ID
        while (accountID < 0) {
            System.out.print("Enter Account ID: ");
            try {
                accountID = scanner.nextInt(); // Read the account ID from user input
                if (accountID < 0) {
                    System.out.println("Account ID must be a positive integer."); // Validate account ID
                }
            } catch (InputMismatchException e) { // Catch if the input is not an integer
                System.out.println("Invalid input. Please enter an integer."); // Inform the user
                scanner.next(); // Clear the invalid input
            }
        }

        // Loop to ensure valid input for deposit amount
        while (amount <= 0) {
            System.out.print("Enter amount to deposit: ");
            try {
                amount = scanner.nextDouble(); // Read the deposit amount from user input
                if (amount <= 0) {
                    System.out.println("Deposit amount must be a positive number."); // Validate amount
                }
            } catch (InputMismatchException e) { // Catch if the input is not a double
                System.out.println("Invalid input. Please enter a number."); // Inform the user
                scanner.next(); // Clear the invalid input
            }
        }

        // Establishing a connection to the database
        try (Connection con = DatabaseConnection.getConnection()) {
            // SQL query to update the account balance by adding the deposit amount
            String updateBalance = "UPDATE Accounts SET Balance = Balance + ? WHERE AccountID = ?";
            PreparedStatement pst1 = con.prepareStatement(updateBalance); // Prepares the SQL statement
            pst1.setDouble(1, amount); // Sets the first parameter (amount) in the prepared statement
            pst1.setInt(2, accountID); // Sets the second parameter (account ID) in the prepared statement
            pst1.executeUpdate(); // Executes the update operation

            // SQL query to log the transaction in the Transactions table
            String logTransaction = "INSERT INTO Transactions (AccountID, TransactionType, Amount) VALUES (?, 'Deposit', ?)";
            PreparedStatement pst2 = con.prepareStatement(logTransaction); // Prepares the SQL statement
            pst2.setInt(1, accountID); // Sets the first parameter (account ID) for the transaction
            pst2.setDouble(2, amount); // Sets the second parameter (amount) for the transaction
            pst2.executeUpdate(); // Executes the insert operation

            // Inform the user that the deposit was successful
            System.out.println("Deposit successful.");
        } catch (SQLException e) {
            // Print stack trace for any SQL exceptions that occur
            e.printStackTrace();
        }
    }
}
