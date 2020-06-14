package banking;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BankingSystem {
    private List<CreditCard> creditCardList;
    private Scanner scanner;
    private CreditCard userSession;
    private Database cardDatabase;

    public BankingSystem(Scanner scanner, Database cardDatabase) {
        this.creditCardList = new ArrayList<>();
        this.scanner = scanner;
        this.cardDatabase = cardDatabase;
    }

    public void createAccount() {
        Random random = new Random();
        int accountNumber = random.nextInt(1000000000);
        int pinCode = random.nextInt(10000);
        int balance = 0;
        CreditCard card = new CreditCard(accountNumber, pinCode);
        System.out.println("");
        System.out.println("Your card has been created");
        System.out.println("");
        System.out.println("Your card number:");
        System.out.println(card.getCardNumber());
        System.out.println("Your card PIN:");
        System.out.printf("%04d", card.getPin());
        creditCardList.add(card);
        cardDatabase.insertCardToDatabase(card.getCardNumber(), card.getPin());
    }

    public boolean logInUser() {
        boolean accessToUserMenu = false;
        System.out.println("Enter your card number:");
        String cardNumberToCheck = scanner.nextLine();
        System.out.println("Enter your PIN:");
        int pinToCheck = Integer.valueOf(scanner.nextLine());
        CreditCard card = cardDatabase.searchCardByCardNumber(cardNumberToCheck, pinToCheck);
        if (card != null) {
            accessToUserMenu = true;
            userSession = card;
        }
        return accessToUserMenu;
    }


    public void logOutUser() {
        userSession = null;
    }

    public void updateBalance(){
        this.userSession.setBalance(cardDatabase.getBalance(this.userSession.getCardNumber()));
    }

    public void showUserBalance() {
        this.updateBalance();
        System.out.println("Balance: " + userSession.getBalance());
    }

    public void addIncome(int income) {
        cardDatabase.addIncome(this.userSession.getCardNumber(), income);
    }

    public void doTransfer() {
        System.out.println("Enter card number:");
        String anotherNumber = scanner.nextLine();
        if (LyhnAlgorithmCheck.check(anotherNumber) == false) {
            System.out.println("Probably you made mistake in the card number.");
            System.out.println("Please try again!");
            return;
        }
        if (this.userSession.getCardNumber().equals(anotherNumber)){
            System.out.println("You can't transfer money to the same account!");
            return;
        }
        if (!cardDatabase.checkCardExist(anotherNumber)){
            System.out.println("Such a card does not exist.");
            return;
        }
        System.out.println("Enter how much money you want to transfer:");
        int moneyToTransfer = Integer.valueOf(scanner.nextLine());
        this.updateBalance();
        if (this.userSession.getBalance() < moneyToTransfer){
            System.out.println("Not enough money!");
            return;
        }
        cardDatabase.transfer(this.userSession.getCardNumber(), anotherNumber, moneyToTransfer);
        System.out.println("Success!");
    }

    public void closeAccount(){
        cardDatabase.deleteAccount(this.userSession.getCardNumber());
        System.out.println("The account has been closed!");
    }
}
