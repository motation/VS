package de.hawhamburg.monopoly.service.brokers.model;

/**
 * Created by Ole on 18.01.2016.
 */
public class Place {
    private String name;

    public Place(String name) {
        this.name = name;
    }

    public Place() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Place place = (Place) o;

        return name != null ? name.equals(place.name) : place.name == null;

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
