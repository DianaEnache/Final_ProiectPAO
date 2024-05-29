package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReviewServiceImpl implements ReviewService{
    private Connection connection;
    public ReviewServiceImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addReview(Integer client_id, Integer game_id, String review_text,Double rating) {
        String query = "INSERT INTO reviews (client_id, game_id, review_text,rating) VALUES (?, ?, ?,?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, client_id);
            statement.setInt(2, game_id);
            statement.setString(3, review_text);
            statement.setDouble(4, rating);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Review-ul a fost adaugat cu succes!");
            }
        } catch (Exception e) {
            System.out.println("Eroare la adaugarea review-ului: " + e.getMessage());
        }
    }

    @Override
    public void deleteReview(Integer review_id) {
        String query = "DELETE FROM reviews WHERE review_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, review_id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Review-ul a fost sters cu succes!");
            } else {
                System.out.println("Nu a fost gasit niciun review cu id-ul respectiv.");
            }
        } catch (Exception e) {
            System.out.println("Eroare la stergerea review-ului: " + e.getMessage());
        }

    }

    @Override
    public void displayReview() {
        String query = "SELECT r.client_id, r.game_id, r.review_text,r.rating, g.game_name, c.client_name " +
                "FROM reviews r " +
                "JOIN game g ON r.game_id = g.game_id " +
                "JOIN client c ON r.client_id = c.client_id";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Integer client_id = resultSet.getInt("client_id");
                Integer game_id = resultSet.getInt("game_id");
                String review_text = resultSet.getString("review_text");
                String game_name = resultSet.getString("game_name");
                String client_name = resultSet.getString("client_name");
                Double rating = resultSet.getDouble("rating");

                System.out.println("Client: " + client_name );
                //System.out.println("ID Client: " + client_id);
                System.out.println("Nume joc: " + game_name );
                //System.out.println("ID Joc: " + game_id);
                System.out.println("Review: " + review_text);
                System.out.println("Rating: " + rating);
                System.out.println("------------------------");
            }
        } catch (Exception e) {
            System.out.println("Eroare la afisarea Review: " + e.getMessage());
        }
    }

    @Override
    public void updateReview(Integer review_id, String column, Object newValue) {
        String query = "UPDATE reviews SET " + column + " = ? WHERE review_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {

            if (newValue instanceof String) {
                statement.setString(1, (String) newValue);
            } else if (newValue instanceof Integer) {
                statement.setInt(1, (Integer) newValue);
            } else if (newValue instanceof Double) {
                statement.setDouble(1, (Double) newValue);
            } else {
                throw new IllegalArgumentException("Error data type");
            }
            statement.setInt(2, review_id);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Review-ul a fost actualizat cu succes!");
            } else {
                System.out.println("Nu a fost gasit niciun review cu id-ul respectiv.");
            }
        } catch (Exception e) {
            System.out.println("Eroare la actualizarea review: " + e.getMessage());
        }

    }

    @Override
    public void displayReviewsByGameName(String gameName) {
        String query = "SELECT r.client_id, r.game_id, r.review_text, r.rating, g.game_name, c.client_name " +
                "FROM reviews r " +
                "JOIN game g ON r.game_id = g.game_id " +
                "JOIN client c ON r.client_id = c.client_id " +
                "WHERE g.game_name = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, gameName);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String review_text = resultSet.getString("review_text");
                    String game_name = resultSet.getString("game_name");
                    String client_name = resultSet.getString("client_name");
                    Double rating = resultSet.getDouble("rating");

                    System.out.println("Client: " + client_name);
                    System.out.println("Nume joc: " + game_name);
                    System.out.println("Review: " + review_text);
                    System.out.println("Rating: " + rating);
                    System.out.println("------------------------");
                }
            }
        } catch (Exception e) {
            System.out.println("Eroare la afisarea recenziilor: " + e.getMessage());
        }
    }
}
