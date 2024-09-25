package bank;
import java.util.Scanner;

public class MainMenu {
    public void display(Scanner scanner) {
        while (true) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Check Balance");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    new Account().createAccount(scanner);
                    break;
                case 2:
                    new Deposit().depositMoney(scanner);
                    break;
                case 3:
                    new Withdraw().withdrawMoney(scanner);
                    break;
                case 4:
                    new Balance().checkBalance(scanner);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
