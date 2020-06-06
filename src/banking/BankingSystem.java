package banking;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BankingSystem {
    private List<CreditCard> creditCardList;
    private Scanner scanner;
    private CreditCard userSession;

    public BankingSystem(Scanner scanner) {
        this.creditCardList = new ArrayList<>();
        this.scanner = scanner;
    }

    public void createAccount() {
        Random random = new Random();
        int accountNumber = random.nextInt(1000000000);
        int pinCode = random.nextInt(10000);
        CreditCard card = new CreditCard(accountNumber, pinCode);
        System.out.println("");
        System.out.println("Your card has been created");
        System.out.println("");
        System.out.println("Your card number:");
        System.out.println(card.getCardNumber());
        System.out.println("Your card PIN:");
        System.out.printf("%04d", card.getPin());
        creditCardList.add(card);
    }

    public boolean logInUser() {
        boolean accessToUserMenu = false;
        System.out.println("Enter your card number:");
        String cardNumberToCheck = scanner.nextLine();
        System.out.println("Enter your PIN:");
        int pinToCheck = Integer.valueOf(scanner.nextLine());
        for (CreditCard card : creditCardList) {
            if (cardNumberToCheck.equals(card.getCardNumber()) && pinToCheck == card.getPin()) {
                accessToUserMenu = true;
                userSession = card;
            }
        }
        return accessToUserMenu;
    }

    public void logOutUser(){
        userSession = null;
    }

    public void showUserBalance(){
        System.out.println("Balance: " + userSession.getBalance());
    }

}
