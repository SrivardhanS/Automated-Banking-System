package bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Withdraw {
    public void withdrawMoney(Scanner scanner) {
        System.out.print("Enter Account ID: ");
        int accountID = scanner.nextInt();
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();

        try (Connection con = DatabaseConnection.getConnection()) {
            String checkBalance = "SELECT Balance FROM Accounts WHERE AccountID = ?";
            PreparedStatement pst1 = con.prepareStatement(checkBalance);
            pst1.setInt(1, accountID);
            ResultSet rs = pst1.executeQuery();

            if (rs.next()) {
                double balance = rs.getDouble(1);
                if (balance >= amount) {
                    String updateBalance = "UPDATE Accounts SET Balance = Balance - ? WHERE AccountID = ?";
                    PreparedStatement pst2 = con.prepareStatement(updateBalance);
                    pst2.setDouble(1, amount);
                    pst2.setInt(2, accountID);
                    pst2.executeUpdate();

                    String logTransaction = "INSERT INTO Transactions (AccountID, TransactionType, Amount) VALUES (?, 'Withdraw', ?)";
                    PreparedStatement pst3 = con.prepareStatement(logTransaction);
                    pst3.setInt(1, accountID);
                    pst3.setDouble(2, amount);
                    pst3.executeUpdate();

                    System.out.println("Withdrawal successful.");
                } else {
                    System.out.println("Insufficient balance.");
                }
            } else {
                System.out.println("Account not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
