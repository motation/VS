package de.hawhamburg.monopoly.service.games.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.hawhamburg.monopoly.util.Components;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ole on 15.11.2015.
 */
public class Game {

    private String gameid;
    private String uri;
    private List<Player> playerList;
//    private Map<String, Player> playersMap;
    private Components components;
    @JsonIgnore
    private int activeTurnOrder = 0;

    public int getActiveTurnOrder(){return activeTurnOrder;}

    private Game(){
        this.components = Components.getComponents();
        playerList = new ArrayList<>();
    }

    public String getPlayers(){
        return components.getGame()+"/"+gameid+"/players";
    }

    public String getGameid() {
        return gameid;
    }

    public String getUri() {
        return uri;
    }

    @JsonIgnore
    public List<Player> getPlayerList() {
        //OF TODO not possible to add new player to the list
        return playerList;
    }

    public static GameBuilder builder(){
        return new GameBuilder();
    }

    public void setActiveTurnOrder(int activeTurnOrder) {
        this.activeTurnOrder = activeTurnOrder;
    }

    public Player getPlayer(String playerId) {
        for(Player pl : playerList)
        {
            if(pl.getId().equals(playerId)){
                return pl;
            }
        }
        return null;
    }

    public boolean addPlayer(Player player){
        return playerList.add(player);
    }

    @Override
    public String toString(){
        GameBuilder g = new GameBuilder();
        return g.toJson(this);
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
            this.game.playerList = players;
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
