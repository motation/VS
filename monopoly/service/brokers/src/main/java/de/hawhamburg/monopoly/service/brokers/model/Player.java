package de.hawhamburg.monopoly.service.brokers.model;

/**
 * Created by Ole on 18.01.2016.
 */
public class Player {
    private String id;
    private String name;
    private String uri;
    private String ready;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getReady() {
        return ready;
    }

    public void setReady(String ready) {
        this.ready = ready;
    }
}
