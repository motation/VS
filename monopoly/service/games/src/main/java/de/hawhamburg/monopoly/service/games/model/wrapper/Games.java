package de.hawhamburg.monopoly.service.games.model.wrapper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.hawhamburg.monopoly.service.games.model.Game;

import java.util.List;

/**
 * Created by Ole on 24.11.2015.
 */
//Wrapper Class
public class Games {
    private List<Game> games;

    private Games() {
    }

    public List<Game> getGames() {
        return games;
    }

    public static GamesBuilder builder(){
        return new GamesBuilder();
    }

    public static class GamesBuilder{
        private Games games;
        public GamesBuilder(){
            games = new Games();
        }
        public GamesBuilder withGames(List<Game> games){
            this.games.games = games;
            return this;
        }

        public Games build(){
            return this.games;
        }

        public Games buildFromJson(String json){
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            return gson.fromJson(json,Games.class);
        }

    }
}
