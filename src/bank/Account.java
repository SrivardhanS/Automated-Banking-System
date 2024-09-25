package bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Account {
    public void createAccount(Scanner scanner) {
        System.out.print("Enter your UserID: ");
        int userID = scanner.nextInt();
        System.out.print("Enter Account Type ID (1 for Savings, 2 for Current): ");
        int typeID = scanner.nextInt();

        try (Connection con = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO Accounts (UserID, TypeID) VALUES (?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, userID);
            pst.setInt(2, typeID);
            pst.executeUpdate();
            System.out.println("Account created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
