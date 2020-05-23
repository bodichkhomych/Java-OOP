package Database;

import entities.Account;
import entities.Card;
import entities.Payment;
import entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DBConnection {

    static Connection createConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/payments", "postgres", "root");
    }


    public static boolean addUser(User user) {
        try {
            Connection connection = createConnection();
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO clients ( name, surname,username, password) VALUES ( ?, ?, ?, ?)");

            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getSecondName());
            stmt.setString(3, user.getUsername());
            stmt.setString(4, user.getPassword());

            connection.setAutoCommit(true);
            stmt.executeUpdate();
            connection.close();

            return true;

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public static void addCard(int userId, String cardName) {
        try {
            Connection connection = createConnection();
            connection.setAutoCommit(false);
            PreparedStatement stmtAccountInsert = connection.prepareStatement("INSERT INTO accounts (balance) VALUES (0)", Statement.RETURN_GENERATED_KEYS);

            int affectedRows = stmtAccountInsert.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating account failed, no rows affected.");
            }

            int id;
            try (ResultSet generatedKeys = stmtAccountInsert.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating account failed, no ID obtained.");
                }
            }

            PreparedStatement stmtCardInsert = connection.prepareStatement("INSERT INTO cards (client_id, account_id, cardname) VALUES (?, ? ,?)");
            stmtCardInsert.setInt(1, userId);
            stmtCardInsert.setInt(2, id);
            stmtCardInsert.setString(3, cardName);

            stmtCardInsert.executeUpdate();

            connection.commit();
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void addPayment(int userId, int cardNo, int sum, String comment) {
        try {
            Connection connection = createConnection();
            connection.setAutoCommit(false);

            PreparedStatement stmtPaymentInsert = connection.prepareStatement("INSERT INTO payments (account_id,pay, comment) VALUES (?,?,?)");
            stmtPaymentInsert.setInt(1, getCardId(userId, cardNo));
            stmtPaymentInsert.setInt(2, sum);
            stmtPaymentInsert.setString(3, comment);

            int affectedRows = stmtPaymentInsert.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating payment failed, no rows affected.");
            }

            PreparedStatement stmtBalanceUpdate = connection.prepareStatement("UPDATE accounts SET balance = balance + ? WHERE account_id = ?");
            stmtBalanceUpdate.setInt(1, sum);
            stmtBalanceUpdate.setInt(2, getCardId(userId, cardNo));

            stmtBalanceUpdate.executeUpdate();
            connection.commit();
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static int getCardId(int userId, int cardNo) {
        int result = -1;
        try {
            Connection connection = createConnection();

            PreparedStatement stmt = connection.prepareStatement("SELECT account_id FROM cards WHERE client_id = ?");
            stmt.setInt(1, userId);
            ResultSet userSet = stmt.executeQuery();

            while (userSet.next()) {
                if (cardNo == 0) {
                    result = userSet.getInt(1);
                    break;
                }
                cardNo--;
            }

            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void setBlocked(int userID, int cardNo, boolean blocked) {
        try {
            Connection connection = createConnection();
            PreparedStatement stmt = connection.prepareStatement("UPDATE cards SET blocked = ? WHERE account_id = ?");
            stmt.setBoolean(1, blocked);
            stmt.setInt(2, getCardId(userID, cardNo));
            stmt.executeUpdate();
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static int checkUser(String username, String password) {
        int clientId = -1;
        try {
            Connection connection = createConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT client_id FROM clients WHERE username = ? AND password = ?");
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                clientId = resultSet.getInt(1);
            }

            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clientId;
    }

    public static List<User> getUsers() {
        List<User> users = new LinkedList<>();
        String sql = "SELECT  client_id, username FROM clients ";
        try {
            Connection connection = createConnection();
            Statement statement = connection.createStatement();
            ResultSet userSet = statement.executeQuery(sql);
            while (userSet.next()) {

                users.add(new User(userSet.getString(2), getCards(userSet.getInt(1))));

            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return users;
    }


    public static List<Card> getCards(int userId) {
        List<Card> cards = new LinkedList<>();
        try {
            Connection connection = createConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT cards.account_id, balance, cardname, blocked\n" +
                    "FROM cards\n" +
                    "         INNER JOIN accounts ON cards.account_id = accounts.account_id\n" +
                    "WHERE cards.client_id = ?");
            stmt.setInt(1, userId);

            ResultSet cardSet = stmt.executeQuery();

            while (cardSet.next()) {
                int balance = cardSet.getInt("balance");
                int accountId = cardSet.getInt("account_id");
                String cardName = cardSet.getString("cardname");
                boolean blocked = cardSet.getBoolean("blocked");


                stmt = connection.prepareStatement("SELECT  pay, comment FROM payments WHERE account_id = ?");
                stmt.setInt(1, accountId);
                ResultSet paymentSet = stmt.executeQuery();
                List<Payment> payments = new ArrayList<>();

                while (paymentSet.next()) {
                    int pay = paymentSet.getInt("pay");
                    String comment = paymentSet.getString("comment");
                    payments.add(new Payment(pay, comment));
                }
                cards.add(new Card(cardName, blocked, new Account(accountId, balance, payments)));
            }

            connection.close();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return cards;
    }

    public static void deleteUser(int id) {
        try {
            Connection connection = createConnection();
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM clients WHERE client_id = ? ");
            stmt.setInt(1, id);

            stmt.executeUpdate();

            connection.close();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void deleteCard(int cardId) {
        try {
            Connection connection = createConnection();
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM cards WHERE account_id = ? ");
            stmt.setInt(1, cardId);

            stmt.executeUpdate();

            connection.close();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void deletePayments(int cardId) {
        try {
            Connection connection = createConnection();
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM payments WHERE account_id = ? ");
            stmt.setInt(1, cardId);

            stmt.executeUpdate();

            connection.close();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
