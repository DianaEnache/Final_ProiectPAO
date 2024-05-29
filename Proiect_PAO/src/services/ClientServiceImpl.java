package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ClientServiceImpl implements ClientService {
    private Connection connection;
    public ClientServiceImpl(Connection connection) {
        this.connection = connection;
    }



    @Override
    public int addClient(String clientName, String clientEmail) {
        String query = "INSERT INTO client (client_name,email) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, clientName);
            statement.setString(2, clientEmail);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new Exception("Id-ul nu a fost obtinut.");
                }
            }
        } catch (Exception e) {
            System.out.println("Eroare adaugare client: " + e.getMessage());
        }
        return -1;
    }

    @Override
    public void deleteClient(String client_name) {
        String query = "DELETE FROM client WHERE client_name = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, client_name);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Clientul a fost sters cu success!.");
            } else {
                System.out.println("Nu exista client cu numele acela.");
            }
        } catch (Exception e) {
            System.out.println("Eroare la stergerea clientului: " + e.getMessage());
        }
    }

    @Override
    public void displayClients() {
        String query = "SELECT * FROM client";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String clientName = resultSet.getString("client_name");
                String email = resultSet.getString("email");

                System.out.println("Nume client: " + clientName);
                System.out.println("Email: " + email);
                System.out.println("------------------------");
            }
        } catch (Exception e) {
            System.out.println("Eroare la afisarea clientilor: " + e.getMessage());
        }
    }

    @Override
    public void updateClient(Integer client_id, String column, String newValue) {
        String query = "UPDATE client SET " + column + " = ? WHERE client_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, newValue);
            statement.setInt(2, client_id);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Clientul a fost actualizat cu succes!");
            } else {
                System.out.println("Nu a fost gasit niciun client cu id-ul respectiv.");
            }
        } catch (Exception e) {
            System.out.println("Eroare la actualizarea clientului: " + e.getMessage());
        }
    }

    public int getLastInsertedClientId() {
        String query = "SELECT MAX(id) AS last_id FROM client";
        int lastId = -1;

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                lastId = resultSet.getInt("last_id");
            }
        } catch (Exception e) {
            System.out.println("Eroare la obtinerea ultimului ID: " + e.getMessage());
        }

        return lastId;
    }

    public int getClientIdByName(String clientName) {
        String query = "SELECT client_id FROM client WHERE client_name = ?";
        int clientId = -1;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, clientName);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                clientId = resultSet.getInt("client_id");
            }
        } catch (Exception e) {
            System.out.println("Eroare la obtinerea ID-ului clientului: " + e.getMessage());
        }

        return clientId;
    }
}
