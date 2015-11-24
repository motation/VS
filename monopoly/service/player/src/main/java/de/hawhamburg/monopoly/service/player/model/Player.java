package de.hawhamburg.monopoly.service.player.model;

import com.mongodb.DBObject;
import com.mongodb.util.JSON;
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

        public Player buildFromResource(String resource) throws IOException {
            this.player = new Player();
            String json = Requester.sendGetRequest(resource);
            DBObject dbObject = (DBObject) JSON.parse(json);
            this.player.id = (String) dbObject.get("id");
            this.player.uri = (String) dbObject.get("uri");
            this.player.ready = (boolean) dbObject.get("ready");
            this.player.position = (int) dbObject.get("position");
            this.player.name = (String) dbObject.get("name");
            this.player.place = (Place) dbObject.get("place");
            return this.player;
        }
    }

}
