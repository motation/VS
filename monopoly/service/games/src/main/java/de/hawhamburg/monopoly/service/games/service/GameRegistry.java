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
        games.add(Game.builder().build());
        games.add(Game.builder().build());
        games.add(Game.builder().build());
        games.add(Game.builder().build());
    }

    public Game addGame(Game game) {
        return games.add(game) ? game : null;
    }

    public List<Game> getGames() {
        return this.games;
    }
}
