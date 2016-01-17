package de.hawhamburg.services.entity;

/**
 * Created by Ole on 17.01.2016.
 */
public class Service {
    String description;
    String name;
    String service;
    String uri;

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
}
