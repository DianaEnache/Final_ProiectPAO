package services;

public interface GameService {
    public void addGame(String game_name, String game_genre,  String developer, Double price, String platforma) ;
    public void  deleteGame(String game_name);
    public void displayGames();
    public void updateGame(String game_name, String column, String newValue);



}
