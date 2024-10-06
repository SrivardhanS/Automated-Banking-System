package bank; // Define the package name

import java.sql.Connection; // Import for SQL Connection
import java.sql.PreparedStatement; // Import for PreparedStatement to execute parameterized SQL queries
import java.sql.ResultSet; // Import for ResultSet to retrieve data from database
import java.sql.SQLException; // Import for handling SQL exceptions
import java.util.InputMismatchException; // Import for handling input mismatch exceptions
import java.util.Scanner; // Import for taking user input

public class Withdraw { // Define the Withdraw class
    public void withdrawMoney(Scanner scanner) { // Method to handle withdrawal process
        int accountID = -1; // Initialize accountID
        double amount = -1; // Initialize amount
        
        // Input validation for Account ID
        while (true) {
            System.out.print("Enter Account ID: "); // Prompt user for Account ID
            try {
                accountID = scanner.nextInt(); // Read Account ID from user input
                break; // Exit loop if input is valid
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a numeric Account ID."); // Error message for invalid input
                scanner.next(); // Clear the invalid input
            }
        }

        // Input validation for withdrawal amount
        while (true) {
            System.out.print("Enter amount to withdraw: "); // Prompt user for withdrawal amount
            try {
                amount = scanner.nextDouble(); // Read amount from user input
                if (amount <= 0) { // Check if amount is positive
                    System.out.println("Amount must be greater than zero."); // Error message for non-positive amount
                } else {
                    break; // Exit loop if input is valid
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a numeric amount."); // Error message for invalid input
                scanner.next(); // Clear the invalid input
            }
        }

        try (Connection con = DatabaseConnection.getConnection()) { // Establish connection to the database
            String checkBalance = "SELECT Balance FROM Accounts WHERE AccountID = ?"; // SQL query to check account balance
            PreparedStatement pst1 = con.prepareStatement(checkBalance); // Prepare the SQL statement
            pst1.setInt(1, accountID); // Set the Account ID parameter in the query
            ResultSet rs = pst1.executeQuery(); // Execute the query and get results

            if (rs.next()) { // Check if the account exists
                double balance = rs.getDouble(1); // Retrieve the current balance
                if (balance >= amount) { // Check if sufficient balance is available
                    String updateBalance = "UPDATE Accounts SET Balance = Balance - ? WHERE AccountID = ?"; // SQL query to update balance
                    PreparedStatement pst2 = con.prepareStatement(updateBalance); // Prepare the update statement
                    pst2.setDouble(1, amount); // Set the withdrawal amount parameter
                    pst2.setInt(2, accountID); // Set the Account ID parameter
                    pst2.executeUpdate(); // Execute the update to deduct the amount

                    String logTransaction = "INSERT INTO Transactions (AccountID, TransactionType, Amount) VALUES (?, 'Withdraw', ?)"; // SQL query to log the transaction
                    PreparedStatement pst3 = con.prepareStatement(logTransaction); // Prepare the log transaction statement
                    pst3.setInt(1, accountID); // Set the Account ID parameter for logging
                    pst3.setDouble(2, amount); // Set the withdrawal amount for logging
                    pst3.executeUpdate(); // Execute the insert to log the transaction

                    System.out.println("Withdrawal successful."); // Notify user of successful withdrawal
                } else {
                    System.out.println("Insufficient balance."); // Notify user of insufficient balance
                }
            } else {
                System.out.println("Account not found."); // Notify user if account does not exist
            }
        } catch (SQLException e) { // Catch and handle SQL exceptions
            e.printStackTrace(); // Print the stack trace of the exception
        }
    }
}
