package bank;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login login = new Login();

        if (login.authenticate(scanner)) {
            MainMenu menu = new MainMenu();
            menu.display(scanner);
        } else {
            System.out.println("Invalid credentials! Please try again.");
        }
    }
}
