package bank;  // This line declares that the class belongs to the 'bank' package.

import java.sql.Connection;  // Importing the Connection interface from the java.sql package.
import java.sql.DriverManager;  // Importing the DriverManager class from the java.sql package.
import java.sql.SQLException;  // Importing the SQLException class from the java.sql package.

public class DatabaseConnection {  // Declaring a public class named DatabaseConnection.
    
    // Defining a constant URL for the database connection string.
    // This includes the database type (MySQL), hostname (localhost), port (3306), and database name (BankDB).
    private static final String URL = "jdbc:mysql://localhost:3306/BankDB?user=root&password=srivardhan@123";

    // Static initializer block: This block runs once when the class is loaded.
    static {
        try {
            // Explicitly load the MySQL JDBC driver class.
            // This step is necessary to register the driver with the DriverManager.
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // Catching the ClassNotFoundException in case the driver class is not found.
            // This indicates that the MySQL JDBC driver is not available in the classpath.
            e.printStackTrace();  // Print the stack trace for debugging purposes.
        }
    }

    // Public static method to obtain a database connection.
    // This method throws SQLException if a database access error occurs.
    public static Connection getConnection() throws SQLException {
        // Using DriverManager to get a connection to the database using the specified URL.
        return DriverManager.getConnection(URL);  // Return the established connection.
    }
}
