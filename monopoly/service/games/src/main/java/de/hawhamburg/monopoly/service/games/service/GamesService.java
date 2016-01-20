package de.hawhamburg.monopoly.service.games.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.hawhamburg.monopoly.service.games.model.Game;
import de.hawhamburg.monopoly.service.games.model.Player;
import de.hawhamburg.monopoly.util.Components;
import de.hawhamburg.monopoly.util.RelaxedSSLValidation;
import de.hawhamburg.monopoly.util.Requester;
import de.hawhamburg.monopoly.util.ServiceNames;
import de.hawhamburg.services.service.ServicesService;
import de.hawhamburg.services.service.UserCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.io.IOException;
import java.util.Base64;
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
//        de.hawhamburg.services.entity.Service sba = services.getServiceByName(ServiceNames.NAME_OF_BANKS_SERVICE);
        de.hawhamburg.services.entity.Service sb = services.getServiceByName(ServiceNames.NAME_OF_BOARDS_SERVICE);
        de.hawhamburg.services.entity.Service sbr = services.getServiceByName(ServiceNames.NAME_OF_BROKERS_SERVICE);
        de.hawhamburg.services.entity.Service sp = services.getServiceByName(ServiceNames.NAME_OF_PLAYER_SERVICE);
        de.hawhamburg.services.entity.Service sg = services.getServiceByName(ServiceNames.NAME_OF_GAMES_SERVICE);
//        de.hawhamburg.services.entity.Service sd = services.getServiceByName(ServiceNames.NAME_OF_DECKS_SERVICE);
        de.hawhamburg.services.entity.Service se = services.getServiceByName(ServiceNames.NAME_OF_EVENTS_SERVICE);
        de.hawhamburg.services.entity.Service sdi = services.getServiceByName(ServiceNames.NAME_OF_DICE_SERVICE);

        Components c = Components.createComonents(sg.getUri(),sdi.getUri(), sb.getUri(), "not our service", sbr.getUri(),"not our service", se.getUri(), sp.getUri());
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
        Player player = Player.builder().withId(playerId).withName(name).withUri(uri).build();
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
        String uri = game.getComponents().getBoard()+ "/" + game.getGameid();
        restTemplate.put(uri, game);
        return true;
    }

    /**
     * Adds the Player to the Board with the Board Service
     * <br><b>Untested</b>
     */
    public boolean addPlayerToBoard(Game game, Player player){
        String uri = game.getComponents().getBoard()+ "/" + game.getGameid() + "/players/"+player.getId();
        HttpEntity entity = new HttpEntity(new HttpHeaders());
        restTemplate.exchange(uri, HttpMethod.PUT, entity, String.class);

        return true;
    }

    public static void main(String[] args) {
        RelaxedSSLValidation.useRelaxedSSLValidation();
        String credentials = UserCredentials.getCredentials();
        String encodedBase64Credentials = Base64.getEncoder().encodeToString(credentials.getBytes());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + encodedBase64Credentials);
        HttpEntity entity = new HttpEntity(Components.getComponents(),headers);

        String uriGames = "https://vs-docker.informatik.haw-hamburg.de/ports/16310/games";
        RestTemplate temp = new RestTemplate();

        ResponseEntity<Game> gameResult = temp.exchange(uriGames, HttpMethod.POST,entity,Game.class);
        System.out.println(gameResult.getBody().toString());
        String playerId = "Loki";
        String joinGameUri = "https://vs-docker.informatik.haw-hamburg.de/ports/16310/games/";
        joinGameUri += gameResult.getBody().getGameid();
        joinGameUri += "/players/"+playerId;
        joinGameUri += "?name="+playerId;
        joinGameUri += "&uri=foobar";
        entity = new HttpEntity(headers);
        temp.exchange(joinGameUri, HttpMethod.PUT, entity, String.class);
    }

}
