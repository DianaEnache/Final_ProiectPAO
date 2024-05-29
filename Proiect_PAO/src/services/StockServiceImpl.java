package services;

import models.Game;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StockServiceImpl  implements StockService{

    private Connection connection;
    public StockServiceImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addStock(Integer product_quantity, Integer game_id) {
        String query = "INSERT INTO stock (product_quantity, game_id) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, product_quantity);
            statement.setInt(2, game_id);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Stocul a fost adaugat cu succes!");
            }
        } catch (Exception e) {
            System.out.println("Eroare la adaugarea stocului: " + e.getMessage());
        }

    }

    @Override
    public void deleteStock(Integer stock_id) {
        String query = "DELETE FROM stock WHERE stock_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, stock_id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Stocul a fost sters cu succes!");
            } else {
                System.out.println("Nu a fost gasit niciun Stoc cu id-ul.");
            }
        } catch (Exception e) {
            System.out.println("Eroare la stergerea stocului: " + e.getMessage());
        }

    }

    @Override
    public void displayStocks() {
        String query = "SELECT s.product_quantity, s.game_id, g.game_name " +
                "FROM stock s " +
                "JOIN game g ON s.game_id = g.game_id";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Integer product_quantity = resultSet.getInt("product_quantity");
                Integer game_id = resultSet.getInt("game_id");
                String game_name = resultSet.getString("game_name");

                System.out.println("Nume joc: " + game_name);
                System.out.println("Cantitate: " + product_quantity);
                System.out.println("Id-ul stocului: " + game_id);
                System.out.println("------------------------");
            }
        } catch (Exception e) {
            System.out.println("Eroare la afisarea stocurilor: " + e.getMessage());
        }

    }

    @Override
    public void updateStock(Integer stock_id, String column, Object newValue) {
        String query = "UPDATE stock SET " + column + " = ? WHERE stock_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            if (newValue instanceof Integer) {
                statement.setInt(1, (Integer) newValue);
            } else if (newValue instanceof Double) {
                statement.setDouble(1, (Double) newValue);
            } else if (newValue instanceof String) {
                statement.setString(1, (String) newValue);
            } else {
                throw new IllegalArgumentException("Error data type");
            }
            statement.setInt(2, stock_id);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Stocul a fost actualizat cu succes!");
            } else {
                System.out.println("Nu a fost gasit niciun stoc cu id-ul respectiv.");
            }
        } catch (Exception e) {
            System.out.println("Eroare la actualizarea stocului: " + e.getMessage());
        }
    }
}
