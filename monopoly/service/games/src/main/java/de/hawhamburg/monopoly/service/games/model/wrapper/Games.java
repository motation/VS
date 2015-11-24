package de.hawhamburg.monopoly.service.games.model.wrapper;

import de.hawhamburg.monopoly.service.games.model.Game;

import java.util.List;

/**
 * Created by Ole on 24.11.2015.
 */
//Wrapper Class
public class Games {
    private List<Game> games;

    public Games(List<Game> games) {
        this.games = games;
    }

    public List<Game> getGames() {
        return games;
    }
}
