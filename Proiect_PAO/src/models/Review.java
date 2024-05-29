package models;

public class Review {
    private Integer review_id;
    private Client client_id;
    private Game game_id;
    private String review_text;

    public Review(Integer review_id,String review_text,Game game_id, Client client_id){
        this.client_id = client_id;
        this.review_text = review_text;
        this.review_id = review_id;
        this.game_id = game_id;
    }


    public Integer getReviewId() {
        return review_id;
    }
    public void setReviewId(Integer review_id) {
        this.review_id = review_id;
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



    public String getReviewText() {
        return review_text;
    }
    public void setReviewText(String review_text) {
        this.review_text = review_text;
    }





}
