package de.hawhamburg.monopoly.service.games.service;

import de.hawhamburg.monopoly.service.games.model.Game;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ole on 24.11.2015.
 */
@Service
public class GameRegistry {
    private List<Game> games;

    @PostConstruct
    public void init() {
        games = new ArrayList<>();
    }

    public Game addGame(Game game) {
        return games.add(game) ? game : null;
    }

    public List<Game> getGames() {
        return this.games;
    }

    public Game findGameById(String gameId) {
        Game searchedGame = null;
        for (Game game : games) {
            if (game.getGameid().equals(gameId)){
                searchedGame = game;
                break;
            }
        }
        return searchedGame;
    }


}
