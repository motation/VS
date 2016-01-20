package de.hawhamburg.services.entity;

/**
 * Created by Ole on 17.01.2016.
 */
public class Service {
    private String description;
    private String name;
    private String service;
    private String uri;
    private String _uri;
    private String status;

    public Service(String description, String name, String service, String uri) {
        this.description = description;
        this.name = name;
        this.service = service;
        this.uri = uri;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getService() {
        return service;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
