package de.hawhamburg.monopoly.service.games.model;

import de.hawhamburg.monopoly.service.player.model.Player;

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

    private Game(){
        players = new HashMap<>();
    }

    public String getGameid() {
        return gameid;
    }

    public String getUri() {
        return uri;
    }

    public List<Player> getPlayers() {
        return new ArrayList<>(players.values());
    }

    public static GameBuilder builder(){
        return new GameBuilder();
    }

    public Player getPlayer(String playerId) {
        Player p = null;
        if(players.containsKey(playerId)) {
            p = players.get(playerId);
        }
        return p;
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

        public Game build(){
            return this.game;
        }
    }
}
