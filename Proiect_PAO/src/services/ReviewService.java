package services;

public interface ReviewService {
    public void addReview(Integer client_id, Integer game_id,String review_text, Double rating) ;
    public void  deleteReview(Integer  review_id);
    public void displayReview();
    public void updateReview(Integer  review_id, String column, Object newValue);

    public void displayReviewsByGameName(String game_name);
}
