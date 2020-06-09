package banking;

import java.sql.*;

public class Database {

    private String databaseName;

    public Database(String dbName){
        this.databaseName = dbName;
        this.createNewDatabase();
        this.createNewTable();
    }
    public void createNewDatabase(){

        String url = "jdbc:sqlite:" + this.databaseName;

        try (Connection connection = DriverManager.getConnection(url)){
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void createNewTable(){
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
        } catch (SQLException e){
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

    public void insertCardToDatabase(String cardNumber, int pinCode){
        String pinText = String.valueOf(pinCode);
        String sql = "INSERT INTO card(number, pin, balance) VALUES (?,?,?)";
        try (Connection connection = this.connect();
             PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setString(1, cardNumber);
            pstmt.setString(2, pinText);
            pstmt.setInt(3, 0);
            pstmt.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
}
