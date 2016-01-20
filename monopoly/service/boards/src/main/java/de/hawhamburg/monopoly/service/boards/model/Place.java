package de.hawhamburg.monopoly.service.boards.model;

/**
 * Created by Marcus Jenz on 25.11.15.
 *
 * @author Marcus Jenz
 */
public class Place {

    private String name;
//    private int id;
    private String uri;

    public String getUri() {
        return uri;
    }

    public Place(){

    }

    public Place(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public int getId() {
//        return id;
//    }

//    public void setId(int id) {
//        this.id = id;
//    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Place place = (Place) o;

        if (name != null ? !name.equals(place.name) : place.name != null) return false;
        return !(uri != null ? !uri.equals(place.uri) : place.uri != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (uri != null ? uri.hashCode() : 0);
        return result;
    }
}
