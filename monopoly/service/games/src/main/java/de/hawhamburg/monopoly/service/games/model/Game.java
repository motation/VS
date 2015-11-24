package de.hawhamburg.monopoly.service.games.model;

import de.hawhamburg.monopoly.service.player.model.Player;

import java.util.List;

/**
 * Created by Ole on 15.11.2015.
 */
public class Game {

    private String gameid;
    private String uri;
    private List<Player> players;

    private Game(){
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

        public Game build(){
            return this.game;
        }

    }
}
