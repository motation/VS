package de.hawhamburg.monopoly.service.player.model;

import de.hawhamburg.monopoly.service.games.model.Place;

/**
 * Created by unknown on 31.10.15.
 */
public class Player {
    private String id;
    private String name;
    private String uri;
    private Place place;
    private int position;
    private boolean ready;

    private Player(){

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUri() {
        return uri;
    }

    public Place getPlace() {
        return place;
    }

    public int getPosition() {
        return position;
    }

    public boolean isReady() {
        return ready;
    }

    public static PlayerBuilder builder(){
        return new PlayerBuilder();
    }

    public static class PlayerBuilder{
        private Player player;

        public PlayerBuilder(){
            this.player = new Player();
        }


        public PlayerBuilder withId(String id){
            this.player.id = id;
            return this;
        }

        public PlayerBuilder withName(String name){
            this.player.name = name;
            return this;
        }

        public PlayerBuilder withUri(String uri){
            this.player.uri = uri;
            return this;
        }

        public PlayerBuilder withPlace(Place place){
            this.player.place = place;
            return this;
        }

        public PlayerBuilder withPosition(int position){
            this.player.position = position;
            return this;
        }

        public PlayerBuilder withReady(boolean ready){
            this.player.ready = ready;
            return this;
        }

        public Player build(){
            return this.player;
        }
    }

}
