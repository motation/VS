package de.hawhamburg.monopoly.client;

import de.hawhamburg.monopoly.service.games.model.Game;
import de.hawhamburg.monopoly.service.games.model.Place;
import de.hawhamburg.monopoly.service.games.model.wrapper.Games;
import de.hawhamburg.monopoly.service.player.model.Player;
import de.hawhamburg.monopoly.util.Components;
import de.hawhamburg.monopoly.util.Requester;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Ole on 25.11.2015.
 */
public class Client {

    private static final String CONTENT_TYPE = "Content-Type";
    private static final String APPLICATION_JSON = "application/json";
    public static final String GET = "GET";
    public static final String POST = "POST";
    public static final String PUT = "PUT";
    private Components components = Components.getComponents();

    private Game game;

    public void createGame(String gameid) throws IOException {

        Game game = Game.builder()
                .withGameid(gameid)
                .withUri(components.getGame())
                .build();

        String resourceUrl = components.getGame();
        URL url = new URL(resourceUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setDoInput(true);
        con.setDoOutput(true);
        con.setRequestMethod(POST);
        con.setRequestProperty(CONTENT_TYPE, APPLICATION_JSON);

        OutputStream out = con.getOutputStream();
        String jsonGame = Game.builder().toJson(game);
        out.write(jsonGame.getBytes("UTF-8"));
        out.flush();
        BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String line = null;
        StringBuffer buffer = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        String json = buffer.toString();
        game = Game.builder().buildFromJson(json);
    }

    public void getGames() throws IOException {
        String json = Requester.sendGetRequest(components.getGame());
        Games games = Games.builder().buildFromJson(json);
        for(Game game : games.getGames()){
            if(game.getGameid().equals("1001")){
                this.game = game;
                break;
            }
        }
    }

    public void joinGame(String gameId,String playerId,String playerName) throws IOException{
        String uri = components.getPlayer()+"/"+playerId;

        String resourceUrl = components.getGame()+"/"+gameId+"/players/"+playerId;
        URL url = new URL(resourceUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setDoInput(true);
        con.setDoOutput(true);
        con.setRequestMethod(PUT);

        con.setRequestProperty(CONTENT_TYPE, APPLICATION_JSON);
        String params = "name="+playerName+"&uri="+uri;

        OutputStream os = con.getOutputStream();
        os.write(params.getBytes("UTF-8"));
        os.flush();

        BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String line = null;
        StringBuffer buffer = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
    }

    public void roll(){

    }

    public void createPlayer(String id, String name) throws IOException{
        Place home = Place.builder()
                .withName("Home")
                .build();
        Player player = Player.builder()
                .withId(id)
                .withName(name)
                .withPosition(0)
                .withPlace(home)
                .withReady(true)
                .withUri(components.getPlayer()+"/"+id)
                .withTurnOrder(0)
                .build();

        String resourceUrl = components.getPlayer();
        URL url = new URL(resourceUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setDoInput(true);
        con.setDoOutput(true);
        con.setRequestMethod(POST);
        con.setRequestProperty(CONTENT_TYPE, APPLICATION_JSON);

        OutputStream out = con.getOutputStream();
        String jsonPlayer = Player.builder().toJson(player);
        out.write(jsonPlayer.getBytes("UTF-8"));
        out.flush();
        BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String line = null;
        StringBuffer buffer = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        System.out.println(buffer.toString());
    }
}
