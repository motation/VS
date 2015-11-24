package de.hawhamburg.monopoly.service.games.model;

import de.hawhamburg.monopoly.service.player.model.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ole on 15.11.2015.
 */
public class Game {

    private String gameid;
    private String uri;
    private List<Player> players;

    private Game(){
        players = new ArrayList<>();
    }

    public String getGameid() {
        return gameid;
    }

    public String getUri() {
        return uri;
    }

    public List<Player> getPlayers() {
        return players;
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

        public GameBuilder withPlayers(List<Player> players){
            this.game.players = players;
            return this;
        }

        public Game build(){
            return this.game;
        }
    }
}
