package de.hawhamburg.monopoly.service.brokers.model;

import de.hawhamburg.monopoly.util.Components;

/**
 * Created by Ole on 19.01.2016.
 */
public class Game {
    private String gameid;
    private String uri;
    private String players;
    private Components components;

    public String getGameid() {
        return gameid;
    }

    public void setGameid(String gameid) {
        this.gameid = gameid;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getPlayers() {
        return players;
    }

    public void setPlayers(String players) {
        this.players = players;
    }

    public Components getComponents() {
        return components;
    }

    public void setComponents(Components components) {
        this.components = components;
    }
}
