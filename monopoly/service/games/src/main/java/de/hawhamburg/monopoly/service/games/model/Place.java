package de.hawhamburg.monopoly.service.games.model;

/**
 * Created by Ole on 24.11.2015.
 */
public class Place {
    private String name;

    private Place(){

    }

    public String getName() {
        return name;
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
