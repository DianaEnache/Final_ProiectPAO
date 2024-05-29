package services;

import models.Game;

public interface StockService {
    public void addStock(Integer stock_quantity, Integer game_id) ;
    public void  deleteStock(Integer stock_id);
    public void displayStocks();
    public void updateStock(Integer stock_id, String column, Object newValue);
}
