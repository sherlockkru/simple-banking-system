package banking;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String databaseName = "";
        for (int i = 0; i < args.length; i++){
            if (args[i].equals("-fileName")){
                databaseName = args[i+1];
            }
        }
        Database database = new Database(databaseName);
        Scanner scanner = new Scanner(System.in);
        BankingSystem bankingSystem = new BankingSystem(scanner, database);
        UserInterface userInterface = new UserInterface(scanner, bankingSystem);
        userInterface.start();
    }
}
