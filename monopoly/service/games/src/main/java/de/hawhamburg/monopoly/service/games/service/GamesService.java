package de.hawhamburg.monopoly.service.games.service;

import de.hawhamburg.monopoly.service.games.model.Game;
import de.hawhamburg.monopoly.service.player.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
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

    public boolean joinGame(String gameId, String playerId, String name, String uri) throws IOException {
        Game game = findGame(gameId);
        Player player = Player.builder().buildFromResource(uri);
        if(playerId.equals(player.getId()) && player.getName().equals(name)){
            game.getPlayers().add(player);
            return true;
        }
        return false;
    }

    public Game findGame(String gameId){
        return gameRegistry.findGameById(gameId);
    }
}
