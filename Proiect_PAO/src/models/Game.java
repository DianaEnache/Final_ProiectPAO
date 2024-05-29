package models;

public class Game {
    private Integer game_id;
    private String game_name;
    private String game_genre;
    private Double price;
    private String platform;
    private String developer;

    public Game(Integer game_id,String game_name,String game_genre, Double price,String platform,String developer){
        this.game_name = game_name;
        this.game_genre = game_genre;
        this.price = price;
        this.platform = platform;
        this.developer = developer;
    }

    public Integer getGameId() {
        return game_id;
    }

    public void setGameId(Integer game_id) {
        this.game_id = game_id;
    }


    public String getGameName() {
        return game_name;
    }
    public void setGameName(String game_name) {
        this.game_name = game_name;
    }


    public String getGameGenre() {
        return game_genre;
    }
    public void setGameGenre(String game_genre) {
        this.game_genre = game_genre;
    }


    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }


    public String getPlatform() {
        return platform;
    }
    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getDeveloper() {
        return developer;
    }
    public void setDeveloper(String developer) {
        this.developer = developer;
    }


}
