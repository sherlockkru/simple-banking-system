package banking;

public class LyhnAlgorithmCheck {

    public static boolean check(String cardNumber){
        String stringCardNumber = cardNumber.substring(0, 15);
        int checkSum;
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
            checkSum = 0;
        } else {
            checkSum = 10 - (sum % 10);
        }

        if (Integer.valueOf(cardNumber.substring(15,16)) == checkSum){
            return true;
        } else {
            return false;
        }
    }
}
