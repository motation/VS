package de.hawhamburg.monopoly.service.events.model;

/**
 * Created by Ole on 18.01.2016.
 */
public class Player {
    private String id;
    private String name;
    private String uri;
    private Place place;
    private int position;
    private boolean ready;


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUri() {
        return uri;
    }

    public Place getPlace() {
        return place;
    }

    public int getPosition() {
        return position;
    }

    public boolean isReady() {
        return ready;
    }
}
