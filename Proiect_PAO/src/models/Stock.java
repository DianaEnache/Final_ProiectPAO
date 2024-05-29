package models;

public class Stock {
    private Integer stock_id;
    private  Integer product_quantity;
    private Game game_id;


    public Stock(Integer stock_id,Integer product_quantity,Game game_id){
        this.stock_id = stock_id;
        this.product_quantity = product_quantity;
        this.game_id = game_id;
    }
    public Integer getStockId() {
        return stock_id;
    }
    public void setStockId(Integer stock_id) {
        this.stock_id = stock_id;
    }



    public Integer getProductQuantity() {
        return product_quantity;
    }
    public void setProductQuantity(Integer product_quantity) {
        this.product_quantity = product_quantity;
    }



    public Game getGame_id() {
        return game_id;
    }
    public void setGame_id(Game game_id) {
        this.game_id = game_id;
    }


}
