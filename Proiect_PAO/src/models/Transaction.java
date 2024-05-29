package models;

public class Transaction {
    protected Integer transaction_id;
    protected Purchase purchase_id;

    public Transaction(Integer transaction_id, Purchase purchase_id) {
        this.transaction_id = transaction_id;
        this.purchase_id = purchase_id;
    }

    public Integer getTransactionId() {
        return transaction_id;
    }

    public void setTransactionId(Integer transaction_id) {
        this.transaction_id = transaction_id;
    }

    public Purchase getPurchaseId() {
        return purchase_id;
    }

    public void setPurchaseId(Purchase purchase) {
        this.purchase_id = purchase;
    }
}
