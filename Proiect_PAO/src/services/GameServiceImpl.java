package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GameServiceImpl implements GameService {


    private Connection connection;
    public GameServiceImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void addGame(String game_name, String game_genre, String developer, Double price, String platform) {
        String query = "INSERT INTO game (game_name, game_genre, developer, price,  platform) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, game_name);
            statement.setString(2, game_genre);
            statement.setString(3, developer);
            statement.setDouble(4, price);
            statement.setString(5, platform);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Jocul a fost adaugat cu succes!");
            }
        } catch (Exception e) {
            System.out.println("Eroare la adaugarea jocului: " + e.getMessage());
        }
    }


    @Override
    public void deleteGame(String game_name) {
        String query = "DELETE FROM game WHERE game_name = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, game_name);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Jocul a fost sters cu succes!");
            } else {
                System.out.println("Nu exista niciun joc cu numele specificat.");
            }
        } catch (Exception e) {
            System.out.println("Eroare la stergerea jocului: " + e.getMessage());
        }
    }

    @Override
    public void displayGames() {
        String query = "SELECT * FROM game";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Integer gameId = resultSet.getInt("game_id");
                String gameName = resultSet.getString("game_name");
                String gameGenre = resultSet.getString("game_genre");
                String developer = resultSet.getString("developer");
                Double price = resultSet.getDouble("price");
                String platform = resultSet.getString("platform");

                System.out.println("Nume joc: " + gameName + " [Game ID: "+ gameId + "] ");
                System.out.println("Gen joc: " + gameGenre);
                System.out.println("Dezvoltator: " + developer);
                System.out.println("Preț: " + price);
                System.out.println("Platforma: " + platform);
                System.out.println("------------------------");
            }
        } catch (Exception e) {
            System.out.println("Eroare la afisarea jocurilor: " + e.getMessage());
        }
    }

    @Override
    public void updateGame(String game_name, String column, String newValue) {
        String query = "UPDATE game SET " + column + " = ? WHERE game_name = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, newValue);
            statement.setString(2, game_name);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Jocul a fost actualizat cu succes!");
            } else {
                System.out.println("Nu exista niciun joc cu numele specificat.");
            }
        } catch (Exception e) {
            System.out.println("Eroare la actualizarea jocului: " + e.getMessage());
        }
    }
}
