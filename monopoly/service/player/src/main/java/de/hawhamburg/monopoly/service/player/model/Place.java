package de.hawhamburg.monopoly.service.player.model;

/**
 * Created by abg631 on 25.11.2015.
 */
public class Place {
    private String name;
    private String uri;
    private Place(){

    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    public static PlaceBuilder builder(){
        return new PlaceBuilder();
    }

    public static class PlaceBuilder{
        private Place place;

        public PlaceBuilder(){
            this.place = new Place();
        }

        public PlaceBuilder withName(String name){
            this.place.name = name;
            return this;
        }

        public Place build(){
            return this.place;
        }
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
