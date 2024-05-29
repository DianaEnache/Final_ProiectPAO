package models;

public class PointsTransaction extends Transaction {
    private Integer points;

    public PointsTransaction(Integer transaction_id, Purchase purchase_id, Integer points) {
        super(transaction_id, purchase_id);
        this.points = points;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }


}
