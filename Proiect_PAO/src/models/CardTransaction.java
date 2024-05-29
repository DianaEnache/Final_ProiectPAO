package models;

public class CardTransaction extends Transaction {
    private String card_number;


    public CardTransaction(Integer transaction_id, Purchase purchase_id, String card_number) {
        super(transaction_id, purchase_id);
        this.card_number = card_number;
    }

    public String getCardNumber() {
        return card_number;
    }

    public void setCardNumber(String card_number) {
        this.card_number = card_number;
    }

}
