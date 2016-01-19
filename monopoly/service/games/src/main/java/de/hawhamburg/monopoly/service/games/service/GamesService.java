package de.hawhamburg.monopoly.service.games.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.hawhamburg.monopoly.service.games.model.Game;
import de.hawhamburg.monopoly.service.games.model.Player;
import de.hawhamburg.monopoly.util.Components;
import de.hawhamburg.monopoly.util.Requester;
import de.hawhamburg.monopoly.util.ServiceNames;
import de.hawhamburg.services.service.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.io.IOException;
import java.util.List;

/**
 * Created by Ole on 04.11.2015.
 */
@Service
public class GamesService {

    @Autowired
    private GameRegistry gameRegistry;

    @Autowired
    private RestTemplate restTemplate;

    public Game createNewGame(ServicesService services){
        //TODO Dice Service DeckService Eventservice
        de.hawhamburg.services.entity.Service sba = services.getServiceByName(ServiceNames.NAME_OF_BANKS_SERVICE);
        de.hawhamburg.services.entity.Service sb = services.getServiceByName(ServiceNames.NAME_OF_BOARDS_SERVICE);
        de.hawhamburg.services.entity.Service sbr = services.getServiceByName(ServiceNames.NAME_OF_BROKERS_SERVICE);
        de.hawhamburg.services.entity.Service sp = services.getServiceByName(ServiceNames.NAME_OF_PLAYER_SERVICE);
        de.hawhamburg.services.entity.Service sg = services.getServiceByName(ServiceNames.NAME_OF_GAMES_SERVICE);
        de.hawhamburg.services.entity.Service sd = services.getServiceByName(ServiceNames.NAME_OF_DECKS_SERVICE);
        de.hawhamburg.services.entity.Service se = services.getServiceByName(ServiceNames.NAME_OF_EVENTS_SERVICE);
        Components c = Components.createComonents(sg.getName(),"DICESERVICE", sb.getUri(), sba.getUri(), sbr.getUri(),sd.getUri(), se.getUri(), sp.getUri());
        return gameRegistry.addGame(c);
    }

    public Game createNewGame(Components comp){
        return gameRegistry.addGame(comp);
    }

    public List<Game> getGames(){
        return gameRegistry.getGames();
    }

    public Player
    joinGame(Game game, String playerId, String name, String uri) throws IOException {
        if(game == null) return null;
        Player player = Player.builder().buildFromResource(uri);
        if(playerId.equals(player.getId()) && player.getName().equals(name)){
            player.setTurnOrder(game.getPlayers().size());
            game.addPlayer(player);
            return player;
        }
        return null;
    }

    public Game findGame(String gameId){
        return gameRegistry.findGameById(gameId);
    }

    public void obtainPlayerMutex(Game game){
        Player tryingPlayer = findPlayerByTurnOrder(game, game.getActiveTurnOrder());
        tryingPlayer.setReady(true);
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

    /**
     * Creates a Board from Board Service
     * <br><b>Untested</b>
     * @param game the game the Board is for
     * @return true for success, false on error
     */
    public boolean createBoard(Game game){
        String uri = game.getComponents().getBoard()+ "/boards/" + game.getGameid();
        restTemplate.put(uri, game);
        return true;
    }

    /**
     * Adds the Player to the Board with the Board Service
     * <br><b>Untested</b>
     */
    public boolean addPlayerToBoard(Game game, Player player){
        String uri = game.getComponents().getBoard()+ "/boards/" + game.getGameid() + "/players/"+player.getId();
        restTemplate.put(uri, player);
        return true;
    }
}
