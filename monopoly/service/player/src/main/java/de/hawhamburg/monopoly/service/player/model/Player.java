package de.hawhamburg.monopoly.service.player.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.hawhamburg.monopoly.service.games.model.Place;
import de.hawhamburg.monopoly.util.Requester;

import java.io.IOException;

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
    private int turnOrder;

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

    public int getTurnOrder(){return turnOrder; }

    public void setTurnOrder(int turnOrder){ this.turnOrder = turnOrder;}

    public void setReady(boolean ready) {
        this.ready = ready;
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

        public PlayerBuilder withTurnOrder(int order){
            this.player.turnOrder = order;
            return this;
        }

        public Player build(){
            return this.player;
        }

        public Player buildFromResource(String resource) throws IOException {
            String json = Requester.sendGetRequest(resource);
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            return gson.fromJson(json,Player.class);
        }
    }
}
