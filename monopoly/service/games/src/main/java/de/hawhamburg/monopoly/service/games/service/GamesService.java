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

    public boolean
    joinGame(String gameId, String playerId, String name, String uri) throws IOException {
        Game game = findGame(gameId);
        if(game == null) return false;
        Player player = Player.builder().buildFromResource(uri);
        if(playerId.equals(player.getId()) && player.getName().equals(name)){
            player.setTurnOrder(game.getPlayers().size());
            game.getPlayers().add(player);
            return true;
        }
        return false;
    }

    public Game findGame(String gameId){
        return gameRegistry.findGameById(gameId);
    }

    public void obtainPlayerMutex(Game game){

        Player tryingPlayer = findPlayerByTurnOrder(game, game.getActiveTurnOrder());
        tryingPlayer.setReady(true);
//        if(tryingPlayer == null) return false;
//        int activeTurnOrder = game.getActiveTurnOrder();
//        return activeTurnOrder == tryingPlayer.getTurnOrder();
    }

    public Player findPlayerByTurnOrder(Game game, int turnOrder){
        List<Player> players = game.getPlayers();
        for(Player player : players){
            if(player.getTurnOrder()== turnOrder){
                return player;

            }
        }
        return null;
    }


    public int dropPlayerMutex(Game game) {
        int order = game.getActiveTurnOrder();
        Player tryingPlayer = findPlayerByTurnOrder(game, game.getActiveTurnOrder());
        tryingPlayer.setReady(false);
        game.setActiveTurnOrder((order+1)%game.getPlayers().size());
        return 0;
    }
}
