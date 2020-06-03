package banking;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankingSystem bankingSystem = new BankingSystem(scanner);
        UserInterface userInterface = new UserInterface(scanner, bankingSystem);
        userInterface.start();
    }
}
