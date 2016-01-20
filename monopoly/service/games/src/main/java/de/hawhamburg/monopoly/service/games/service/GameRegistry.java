package de.hawhamburg.monopoly.service.games.service;

import de.hawhamburg.monopoly.service.games.model.Game;
import de.hawhamburg.monopoly.util.Components;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    public Game addGame(Components components) {
        Game game = Game.builder()
                .withGameid(System.currentTimeMillis()+"")
                .withUri("")
                .withComponents(components)
                .build();
        if(!games.add(game)) game = null;
        return game;
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
