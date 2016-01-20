package de.hawhamburg.monopoly.service.events.model;

/**
 * Created by Ole on 18.01.2016.
 */
public class Event {
    private String type;
    private String name;
    private String reason;
    private String resource;
    private String uri;
    private Player player;

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getReason() {
        return reason;
    }

    public String getResource() {
        return resource;
    }

    public Player getPlayer() {
        return player;
    }

    public String getUri() {return uri;}

    public void setUri(String uri){ this.uri = uri; }

}
