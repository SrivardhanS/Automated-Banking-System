package bank; // Declares the package name for this class

import java.sql.Connection; // Imports the Connection class for establishing database connections
import java.sql.PreparedStatement; // Imports the PreparedStatement class for executing parameterized SQL queries
import java.sql.ResultSet; // Imports the ResultSet class for retrieving results from SQL queries
import java.sql.SQLException; // Imports the SQLException class for handling SQL-related exceptions
import java.util.InputMismatchException; // Imports InputMismatchException class for handling invalid input
import java.util.Scanner; // Imports the Scanner class for taking user input

public class Balance { // Declares the Balance class
    // Method to check the account balance
    public void checkBalance(Scanner scanner) {
        int accountID = -1; // Initialize accountID to an invalid value
        boolean validInput = false; // Flag to track valid input

        // Loop until a valid integer is entered
        while (!validInput) {
            System.out.print("Enter Account ID: ");
            try {
                accountID = scanner.nextInt(); // Read the account ID from user input
                validInput = true; // Set flag to true if input is valid
            } catch (InputMismatchException e) { // Catch if the input is not an integer
                System.out.println("Invalid input. Please enter an integer."); // Inform the user
                scanner.next(); // Clear the invalid input
            }
        }

        // Establishing a connection to the database
        try (Connection con = DatabaseConnection.getConnection()) {
            // SQL query to select the balance for the given account ID
            String query = "SELECT Balance FROM Accounts WHERE AccountID = ?";
            PreparedStatement pst = con.prepareStatement(query); // Prepares the SQL statement
            pst.setInt(1, accountID); // Sets the first parameter (account ID) in the prepared statement
            ResultSet rs = pst.executeQuery(); // Executes the query and retrieves results

            // Check if a result is returned
            if (rs.next()) {
                // Output the balance for the account ID
                System.out.println("Account Balance: " + rs.getDouble(1));
            } else {
                // Output message if the account is not found
                System.out.println("Account not found.");
            }
        } catch (SQLException e) {
            // Print stack trace for any SQL exceptions that occur
            e.printStackTrace();
        }
    }
}
