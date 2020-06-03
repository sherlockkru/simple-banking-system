package banking;

public class CreditCard {
    private static int BIN = 400000;
    private int accountNumber;
    private int checksum;
    private int pin;
    private String cardNumber;
    private int balance;

    public CreditCard(int accountNumber, int pin, int checksum){
        this.cardNumber = String.valueOf(BIN) + String.valueOf(accountNumber)
                + String.valueOf(checksum);
        this.balance = 0;
        this.pin = pin;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public int getPin() {
        return pin;
    }

    public int getBalance() {
        return balance;
    }
}
