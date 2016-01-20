package de.hawhamburg.monopoly.service.games.service;

import de.hawhamburg.monopoly.service.games.model.Game;
import de.hawhamburg.monopoly.util.Components;
import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOG = LoggerFactory.getLogger(GameRegistry.class);


    @PostConstruct
    public void init() {
        games = new ArrayList<>();
    }

    public Game addGame(Components components) {

        String id = System.currentTimeMillis()+"";
        LOG.info("Trying to create game with id" + id);
        Game game = Game.builder()
                .withGameid(id)
                .withUri(components.getGame()+"/"+id)
                .withComponents(components)
                .build();
        if(!games.add(game)) {
            game = null;
            LOG.warn("Could not create game with id "+ id);
        }else{
            LOG.info("Created game with id" + id);
        }

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
