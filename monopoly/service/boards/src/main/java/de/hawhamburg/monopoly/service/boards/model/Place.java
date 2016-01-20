package de.hawhamburg.monopoly.service.boards.model;

/**
 * Created by Marcus Jenz on 25.11.15.
 *
 * @author Marcus Jenz
 */
public class Place {

    private String name;
    private int id;

    public Place(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Place place = (Place) o;

        return name.equals(place.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
