package banking;

public class CreditCard {
    private final int BIN = 400000;
    private int accountNumber;
    private int checksum;
    private int pin;
    private String cardNumber;
    private int balance;

    public CreditCard(int accountNumber, int pin) {
        this.checksum = this.luhnAlgorithm(BIN, accountNumber);
        this.cardNumber = String.valueOf(BIN) + accountNumberToString(accountNumber)
                + checksum;
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

    private String accountNumberToString(int accountNumber) {
        return String.format("%09d", accountNumber);
    }

    private int luhnAlgorithm(int BIN, int accountNumber) {
        String stringCardNumber = String.valueOf(BIN) + accountNumberToString(accountNumber);
        int sum = 0;
        int stringLength = stringCardNumber.length();

        for (int i = 0; i < stringLength; i = i + 2) {
            int num = Character.getNumericValue(stringCardNumber.charAt(i));
            num = num * 2;
            if (num > 9) {
                num -= 9;
            }
            sum += num;
        }
        for (int i = 1; i < stringLength; i = i + 2) {
            int num = Character.getNumericValue(stringCardNumber.charAt(i));
            sum += num;
        }
        if (sum % 10 == 0) {
            return 0;
        } else {
            return 10 - (sum % 10);
        }
    }
}
