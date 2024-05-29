package services;

import models.Client;

public interface ClientService {
    public int addClient(String client_name, String email) ;
    public void  deleteClient(String client_name);
    public void displayClients();
    public void updateClient(Integer client_id, String column, String newValue);
    public int getLastInsertedClientId();
    public int getClientIdByName(String clientName);


}
