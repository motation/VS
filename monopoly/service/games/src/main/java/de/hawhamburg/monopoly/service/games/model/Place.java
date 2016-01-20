package de.hawhamburg.monopoly.service.games.model;

/**
 * Created by Ole on 24.11.2015.
 */
public class Place {
    private String name;
    private String uri;

    private Place(){

    }

    public String getUri() {
        return uri;
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

        public PlaceBuilder withUri(String uri){
            this.place.uri = uri;
            return this;
        }

        public Place build(){
            return this.place;
        }
    }


}
