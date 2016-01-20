package de.hawhamburg.monopoly.service.boards.model;

import de.hawhamburg.monopoly.service.boards.model.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ole on 15.11.2015.
 */
public class Board {

    private Map<String, Player> players;
    private final static int PRISON = 3;
    private final Game game;
    private List<Place> places;
    private String uri;


    public String getGameId() {
        return game.getGameid();
    }

    public Board(Game game){
        players = new HashMap<>();
        places = new ArrayList<>();
        uri = game.getComponents().getBoard()+"/"+System.currentTimeMillis();
        this.game = game;
    }

    public int getPosition(String playerId) {
        if(players.containsKey(playerId)){
            return players.get(playerId).getPosition();
        }
        Player player = new Player(playerId);
        player.setPosition(0);
        player.setPlace(places.get(player.getPosition()));
            players.put(playerId, player);
            return player.getPosition();
    }

    public int moveByRoll(String playerId, int roll) {
        Player player = players.get(playerId);
        //TODO was tun wenn der Spieler nicht existiert?
        int newPosition = (player.getPosition()+roll)%places.size();
        player.setPosition(newPosition);
        player.setPlace(places.get(player.getPosition()));
//        int prevPosition = player.getPosition();
        return player.getPosition();
    }

    public int moveToPrison(String playerId) {
        Player player = players.get(playerId);
        player.setPosition(PRISON);
        return PRISON;
    }

    public Player getPlayer(String playerId){
        if(players.containsKey(playerId)){
            return players.get(playerId);
        }
        Player player = new Player(playerId);
        players.put(playerId, player);
        return player;
    }

    public List<Player> getPlayers(){
        return new ArrayList<>(players.values());
    }

    public Player removePlayer(String playerId) {
        return players.remove(playerId);
    }

    public List<Place> getPlaces() {
        return places;
    }

    public Place addPlace(Place place) {
        if (places == null)
            places = new ArrayList<>();
//        Place place = new Place(placeName);
//        if(!places.contains(place)){

            places.add(place);
//        }

        return place;
    }

    public String getUri() {
        return uri;
    }
}
