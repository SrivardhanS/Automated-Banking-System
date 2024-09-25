package bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Balance {
    public void checkBalance(Scanner scanner) {
        System.out.print("Enter Account ID: ");
        int accountID = scanner.nextInt();

        try (Connection con = DatabaseConnection.getConnection()) {
            String query = "SELECT Balance FROM Accounts WHERE AccountID = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, accountID);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                System.out.println("Account Balance: " + rs.getDouble(1));
            } else {
                System.out.println("Account not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
