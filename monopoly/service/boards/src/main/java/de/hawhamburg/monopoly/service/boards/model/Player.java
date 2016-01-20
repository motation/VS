package de.hawhamburg.monopoly.service.boards.model;

/**
 * Created by Marcus Jenz on 25.11.15.
 *
 * @author Marcus Jenz
 */
public class Player {

    private String id;
    private Place place;
    private int position;
    private String uri;

    public Player(String playerId) {
        id = playerId;
        position = 0;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getId() {
        return id;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
