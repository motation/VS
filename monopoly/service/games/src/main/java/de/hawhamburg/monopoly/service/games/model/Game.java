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
    private List<Player> players;
//    private Map<String, Player> playersMap;
    private Components components;
    @JsonIgnore
    private int activeTurnOrder = 0;

    public int getActiveTurnOrder(){return activeTurnOrder;}

    private Game(){
        this.components = Components.getComponents();
        players = new ArrayList<>();
    }

    public String getGameid() {
        return gameid;
    }

    public String getUri() {
        return uri;
    }

    public List<Player> getPlayers() {
        //OF TODO not possible to add new player to the list
        return players;
    }

    public static GameBuilder builder(){
        return new GameBuilder();
    }

    public void setActiveTurnOrder(int activeTurnOrder) {
        this.activeTurnOrder = activeTurnOrder;
    }

    public Player getPlayer(String playerId) {
        for(Player pl : players)
        {
            if(pl.getId().equals(playerId)){
                return pl;
            }
        }
        return null;
    }

    public boolean addPlayer(Player player){
        return players.add(player);
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
            this.game.players = players;
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
