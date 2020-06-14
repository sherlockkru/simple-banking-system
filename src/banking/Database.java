package banking;

import java.sql.*;

public class Database {

    private String databaseName;

    public Database(String dbName) {
        this.databaseName = dbName;
        this.createNewDatabase();
        this.createNewTable();
    }

    public void createNewDatabase() {

        String url = "jdbc:sqlite:" + this.databaseName;

        try (Connection connection = DriverManager.getConnection(url)) {
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createNewTable() {
        String url = "jdbc:sqlite:" + this.databaseName;

        String sql = "CREATE TABLE IF NOT EXISTS card (\n"
                + " id INTEGER PRIMARY KEY AUTOINCREMENT, \n"
                + " number TEXT, \n"
                + "pin TEXT, \n"
                + " balance INTEGER DEFAULT 0 \n"
                + ");";

        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private Connection connect() {
        String url = "jdbc:sqlite:" + this.databaseName;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void insertCardToDatabase(String cardNumber, int pinCode) {
        String pinText = String.valueOf(pinCode);
        String sql = "INSERT INTO card(number, pin, balance) VALUES (?,?,?)";
        try (Connection connection = this.connect();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, cardNumber);
            pstmt.setString(2, pinText);
            pstmt.setInt(3, 0);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public CreditCard searchCardByCardNumber(String cardNumber, int pinToCheck) {
        String sql = "SELECT * FROM card WHERE number = ? AND pin = ?";
        try (Connection connection = this.connect();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, cardNumber);
            pstmt.setInt(2, pinToCheck);

            ResultSet rs = pstmt.executeQuery();


            if (rs.next()) {
                return new CreditCard(rs.getString("number"), rs.getInt("pin"), rs.getInt("balance"));
            } else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public boolean checkCardExist(String cardNumber) {
        String sql = "SELECT * FROM card WHERE number = ?";
        try (Connection connection = this.connect();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, cardNumber);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    public void addIncome(String cardNumber, int income){
        String sql = "UPDATE card SET balance = balance + ? "
                + "WHERE number = ?";
        try (Connection connection = this.connect();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, income);
            pstmt.setString(2, cardNumber);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addLoss(String cardNumber, int loss){
            String sql = "UPDATE card SET balance = balance - ? "
                    + "WHERE number = ?";
            try (Connection connection = this.connect();
                 PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setInt(1, loss);
                pstmt.setString(2, cardNumber);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
    }

    public int getBalance(String cardNumber){
        int returnBalance = 0;
        String sql = "SELECT balance FROM card WHERE number = ?";
        try (Connection connection = this.connect();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, cardNumber);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                returnBalance = rs.getInt("balance");
            } else {
                return 0;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return returnBalance;
    }

    public void transfer(String userCard, String anotherCard, int amount){
        this.addLoss(userCard, amount);
        this.addIncome(anotherCard, amount);
    }

    public void deleteAccount(String cardNumber){
        String sql = "DELETE FROM card WHERE number = ?";
        try (Connection connection = this.connect();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, cardNumber);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
