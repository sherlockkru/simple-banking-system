package banking;

import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;
    private BankingSystem bankingSystem;

    public UserInterface(Scanner scanner, BankingSystem bankingSystem) {
        this.scanner = scanner;
        this.bankingSystem = bankingSystem;
    }

    public void start() {
        while (true) {
            this.showMainMenu();
            int command = Integer.valueOf(scanner.nextLine());

            switch (command) {
                case 1:
                    bankingSystem.createAccount();
                    break;
                case 2:
                    if (bankingSystem.logInUser() == true) {
                        System.out.println("");
                        System.out.println("You have successfully logged in!");
                        this.showUserMenu();
                    } else {
                        System.out.println("Wrong card number or PIN!");
                    }
                    break;
                case 0:
                    System.exit(1);
                default:
                    System.out.println("Incorrect command");
            }
        }
    }

    public void showMainMenu() {
        System.out.println("");
        System.out.println("1. Create an account");
        System.out.println("2. Log into account");
        System.out.println("0. Exit");

    }

    public void showUserMenu() {
        boolean logOut = false;
        while (!logOut) {
            System.out.println("");
            System.out.println("1. Balance");
            System.out.println("2. Add income");
            System.out.println("3. Do transfer");
            System.out.println("4. Close account");
            System.out.println("5. Log out");
            System.out.println("0. Exit");
            int command = Integer.valueOf(scanner.nextLine());

            switch (command) {
                case 1:
                    bankingSystem.showUserBalance();
                    break;
                case 2:
                    System.out.println("Enter income:");
                    int income = Integer.valueOf(scanner.nextLine());
                    bankingSystem.addIncome(income);
                    break;
                case 3:
                    System.out.println("Transfer");
                    bankingSystem.doTransfer();
                    break;
                case 4:
                    bankingSystem.closeAccount();
                    bankingSystem.logOutUser();
                    logOut = true;
                    break;
                case 5:
                    logOut = true;
                    bankingSystem.logOutUser();
                    break;
                case 0:
                    System.exit(1);
                default:
                    System.out.println("Incorrect command");
            }
        }
    }
}
