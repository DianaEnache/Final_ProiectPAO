package services;

public interface PurchaseService {
    public void addPurchase(Integer client_id, Integer game_id,String transaction_type) ;
    public void  deletePurchase(Integer  purchase_id);
    public void displayPurchase();
    public void updatePurchase(Integer  purchase_id, String column, Object newValue);
    public void  displayPurchaseByClient(Integer client_id);
}
