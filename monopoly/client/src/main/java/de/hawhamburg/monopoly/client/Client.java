package de.hawhamburg.monopoly.client;


import de.hawhamburg.monopoly.model.Game;
import de.hawhamburg.monopoly.util.Components;
import de.hawhamburg.monopoly.util.Requester;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
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

    public void createGame(String gameid) throws IOException {
        URL url = new URL("http://localhost:4567/games");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setDoInput(true);
        con.setRequestMethod("POST");
//        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(con.getOutputStream()));

        BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

        String line = null;
        while ((line=reader.readLine())!=null){
            System.out.println(line);
        }
        reader.close();
        con.disconnect();
    }
}
