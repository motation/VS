package de.hawhamburg.monopoly.service.events.model;

/**
 * Created by Ole on 18.01.2016.
 */
public class Subscription {
    private String gameid;
    private String uri;
    private String callbackuri;
    private Event event;


    public String getGameid() {
        return gameid;
    }

    public String getUri() {
        return uri;
    }

    public String getCallbackuri() {
        return callbackuri;
    }

    public Event getEvent() {
        return event;
    }
}
