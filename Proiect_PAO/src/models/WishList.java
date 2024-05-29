package models;

import java.util.ArrayList;
import java.util.List;
//Tabel Wishlist si ia dupa id ul clientului jocurile dorite
public class WishList {
        private List<Game> games = new ArrayList<>();

        public void addGame(Game game) {
            games.add(game);
        }

        public void deleteGame(Game game) {
            games.remove(game);
        }
}
