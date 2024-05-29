package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PurchaseServiceImpl implements PurchaseService {
    private Connection connection;
    public PurchaseServiceImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void addPurchase(Integer client_id, Integer game_id, String transaction_type) {
        String query = "INSERT INTO purchase (client_id, game_id, transaction_type, total_price) VALUES (?,?,?, (SELECT price FROM game WHERE game_id = ?))";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, client_id);
            statement.setInt(2, game_id);
            statement.setString(3, transaction_type);
            statement.setInt(4, game_id);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Achizitie adaugata cu success!");
            }
        } catch (Exception e) {
            System.out.println("Eroare la adaugarea achizitiei: " + e.getMessage());
        }
    }

    @Override
    public void deletePurchase(Integer purchase_id) {
        String deleteCardTransactionQuery = "DELETE FROM cardtransaction WHERE purchase_id = ?";
        String deletePointsTransactionQuery = "DELETE FROM pointstransaction WHERE purchase_id = ?";
        String deletePurchaseQuery = "DELETE FROM purchase WHERE purchase_id = ?";

        try (PreparedStatement cardTransactionStatement = connection.prepareStatement(deleteCardTransactionQuery);
             PreparedStatement pointsTransactionStatement = connection.prepareStatement(deletePointsTransactionQuery);
             PreparedStatement purchaseStatement = connection.prepareStatement(deletePurchaseQuery)) {

            // sterge din tabela cardtransaction
            cardTransactionStatement.setInt(1, purchase_id);
            cardTransactionStatement.executeUpdate();

            // sterge din tabela pointstransaction
            pointsTransactionStatement.setInt(1, purchase_id);
            pointsTransactionStatement.executeUpdate();

            // sterge din tabela purchase
            purchaseStatement.setInt(1, purchase_id);
            int rowsDeleted = purchaseStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Achizitia a fost stearsa cu success!");
            } else {
                System.out.println("nu a fost gasita nicio achizitie cu id-ul respectiv.");
            }
        } catch (Exception e) {
            System.out.println("Eroare la stergerea achizitiei: " + e.getMessage());
        }
    }


    @Override
    public void displayPurchase() {
        String query = "SELECT p.client_id, p.game_id, p.transaction_type, p.total_price, g.game_name, c.client_name " +
                "FROM purchase p " +
                "JOIN game g ON p.game_id = g.game_id " +
                "JOIN client c ON p.client_id = c.client_id";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Integer client_id = resultSet.getInt("client_id");
                Integer game_id = resultSet.getInt("game_id");
                String transaction_type = resultSet.getString("transaction_type");
                Double total_price = resultSet.getDouble("total_price");
                String game_name = resultSet.getString("game_name");
                String client_name = resultSet.getString("client_name");

                System.out.println("Client: " + client_name + " [id: " + client_id + " ]");
                //System.out.println("ID Client: " + client_id);
                System.out.println("Nume joc: " + game_name + " [id: " + game_id + " ]");
                //System.out.println("ID Joc: " + game_id);
                System.out.println("Tip tranzactie: " + transaction_type);
                System.out.println("Pret: " + total_price);
                System.out.println("------------------------");
            }
        } catch (Exception e) {
            System.out.println("Eroare la afisarea achizitiilor: " + e.getMessage());
        }
    }


    @Override
    public void updatePurchase(Integer purchase_id, String column, Object newValue) {
        String query = "UPDATE purchase SET " + column + " = ? WHERE purchase_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {

            //in functie de type
            if (newValue instanceof String) {
                statement.setString(1, (String) newValue);
            } else if (newValue instanceof Integer) {
                statement.setInt(1, (Integer) newValue);
            } else if (newValue instanceof Double) {
                statement.setDouble(1, (Double) newValue);
            } else {
                throw new IllegalArgumentException("Error value");
            }
            statement.setInt(2, purchase_id);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Achizitia a fost actualizata cu success!");
            } else {
                System.out.println("Nu a fost gasita nicio achizitie cu id-ul respectiv.");
            }
        } catch (Exception e) {
            System.out.println("Eroare la actualizarea achizitiei: " + e.getMessage());
        }
    }

    public void displayPurchaseByClient(Integer client_id) {
        String query = "SELECT p.client_id, p.game_id, p.transaction_type, p.total_price, g.game_name, c.client_name " +
                "FROM purchase p " +
                "JOIN game g ON p.game_id = g.game_id " +
                "JOIN client c ON p.client_id = c.client_id " +
                "WHERE p.client_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, client_id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Integer game_id = resultSet.getInt("game_id");
                String transaction_type = resultSet.getString("transaction_type");
                Double total_price = resultSet.getDouble("total_price");
                String game_name = resultSet.getString("game_name");
                String client_name = resultSet.getString("client_name");

                System.out.println("Client: " + client_name + " [id: " + client_id + " ]");
                System.out.println("Nume joc: " + game_name + " [id: " + game_id + " ]");
                System.out.println("Tip tranzacție: " + transaction_type);
                System.out.println("Pret" + total_price);
                System.out.println("------------------------");
            }
        } catch (Exception e) {
            System.out.println("Eroare la afisarea achizitiilor: " + e.getMessage());
        }
    }
}
