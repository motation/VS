package de.hawhamburg.monopoly.service.games.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.hawhamburg.monopoly.util.Components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ole on 15.11.2015.
 */
public class Game {

    private String gameid;
    private String uri;
    private Map<String, Player> players;
    private Components components;
    @JsonIgnore
    private int activeTurnOrder = 0;

    public int getActiveTurnOrder(){return activeTurnOrder;}

    private Game(){
        this.components = Components.getComponents();
        players = new HashMap<>();
    }

    public String getGameid() {
        return gameid;
    }

    public String getUri() {
        return uri;
    }

    public List<Player> getPlayers() {
        //OF TODO not possible to add new player to the list
        return new ArrayList<>(players.values());
    }

    public static GameBuilder builder(){
        return new GameBuilder();
    }

    public void setActiveTurnOrder(int activeTurnOrder) {
        this.activeTurnOrder = activeTurnOrder;
    }

    public Player getPlayer(String playerId) {
        Player p = null;
        if(players.containsKey(playerId)) {
            p = players.get(playerId);
        }
        return p;
    }

    public boolean addPlayer(Player player){
        Player p = players.put(player.getId(), player);
        return p == null;
    }

    public Components getComponents(){
        return components;
    }

    public static class GameBuilder{
        private Game game;

        public GameBuilder(){
            this.game = new Game();
        }

        public GameBuilder withGameid(String gameid){
            this.game.gameid = gameid;
            return this;
        }

        public GameBuilder withUri(String uri){
            this.game.uri = uri;
            return this;
        }

        public GameBuilder withPlayers(List<Player> players){
            for(Player player : players){
                this.game.players.put(player.getId(), player);
            }
            return this;
        }
        
        public GameBuilder withComponents(Components components) {
            this.game.components = components;
            return this;
        }

        public Game buildFromJson(String json){
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            return gson.fromJson(json,Game.class);
        }

        public String toJson(Game game){
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            return (gson.toJson(game));
        }

        public Game build(){
            return this.game;
        }
    }
}
