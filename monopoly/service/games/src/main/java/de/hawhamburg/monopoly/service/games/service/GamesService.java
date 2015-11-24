package de.hawhamburg.monopoly.service.games.service;

import de.hawhamburg.monopoly.service.games.model.Game;
import de.hawhamburg.monopoly.service.player.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ole on 04.11.2015.
 */
@Service
public class GamesService {

    @Autowired
    private GameRegistry gameRegistry;

    public Game createNewGame(Game game){
        return gameRegistry.addGame(game);
    }

    public List<Game> getGames(){
        return gameRegistry.getGames();
    }

    public boolean joinGame(String gameId, String playerId, String name, String uri) {
        //OF TODO join the game
        return false;
    }
}
