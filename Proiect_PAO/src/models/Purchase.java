package models;

public class Purchase {
    private Integer purchase_id;
    private Client client_id;
    private Game game_id;
    private String transaction_type;
    private Double total_price;

    public Purchase(Integer purchase_id, Client client_id,Game game_id,String transaction_type, Double total_price){
        this.purchase_id = purchase_id;
        this.client_id = client_id;
        this.game_id = game_id;
        this.transaction_type = transaction_type;
        this.total_price = total_price;
    }


    public Integer getPurchaseId() {
        return purchase_id;
    }
    public void setPurchaseId(Integer purchase_id) {
        this.purchase_id = purchase_id;
    }


    public Client getClient_id() {
        return client_id;
    }

    public void setClient_id(Client client_id) {
        this.client_id = client_id;
    }

    public Game getGame_id() {
        return game_id;
    }

    public void setGame_id(Game game_id) {
        this.game_id = game_id;
    }


    public String getTransactionType() {
        return transaction_type;
    }
    public void setTransactionType(String transaction_type) {
        this.transaction_type = transaction_type;
    }



    public Double getTotalPrice() {
        return total_price;
    }
    public void setTotalPrice(Double total_price) {
        this.total_price = total_price;
    }

}
