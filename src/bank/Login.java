package bank; // Define the package name

import java.sql.Connection; // Import for SQL Connection
import java.sql.PreparedStatement; // Import for PreparedStatement to execute parameterized SQL queries
import java.sql.ResultSet; // Import for ResultSet to retrieve data from database
import java.sql.SQLException; // Import for handling SQL exceptions
import java.util.Scanner; // Import for taking user input

public class Login { // Define the Login class
    public boolean authenticate(Scanner scanner) { // Method to authenticate user login
        System.out.print("Enter username: "); // Prompt user for username
        String username = scanner.next(); // Read username from user input
        System.out.print("Enter password: "); // Prompt user for password
        String password = scanner.next(); // Read password from user input

        try (Connection con = DatabaseConnection.getConnection()) { // Establish connection to the database
            // SQL query to check if the provided username and password exist in the Users table
            String query = "SELECT * FROM Users WHERE Username = ? AND Password = ?";
            PreparedStatement pst = con.prepareStatement(query); // Prepare the SQL statement
            pst.setString(1, username); // Set the username parameter in the query
            pst.setString(2, password); // Set the password parameter in the query
            ResultSet rs = pst.executeQuery(); // Execute the query and get results

            return rs.next(); // Return true if a matching user is found, otherwise return false
        } catch (SQLException e) { // Catch and handle SQL exceptions
            e.printStackTrace(); // Print the stack trace of the exception
            return false; // Return false in case of an exception
        }
    }
}
