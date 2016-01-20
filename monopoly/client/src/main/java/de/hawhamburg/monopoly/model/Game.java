package de.hawhamburg.monopoly.model;

/**
 * Created by Ole on 15.01.2016.
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by Ole on 15.11.2015.
 */
public class Game {

    private String gameid;
    private String uri;
    private int activeTurnOrder = 0;

    public String getGameid() {
        return gameid;
    }

    public String getUri() {
        return uri;
    }

    public static GameBuilder builder(){
        return new GameBuilder();
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
