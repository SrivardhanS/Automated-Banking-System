package bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Deposit {
    public void depositMoney(Scanner scanner) {
        System.out.print("Enter Account ID: ");
        int accountID = scanner.nextInt();
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();

        try (Connection con = DatabaseConnection.getConnection()) {
            String updateBalance = "UPDATE Accounts SET Balance = Balance + ? WHERE AccountID = ?";
            PreparedStatement pst1 = con.prepareStatement(updateBalance);
            pst1.setDouble(1, amount);
            pst1.setInt(2, accountID);
            pst1.executeUpdate();

            String logTransaction = "INSERT INTO Transactions (AccountID, TransactionType, Amount) VALUES (?, 'Deposit', ?)";
            PreparedStatement pst2 = con.prepareStatement(logTransaction);
            pst2.setInt(1, accountID);
            pst2.setDouble(2, amount);
            pst2.executeUpdate();

            System.out.println("Deposit successful.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
