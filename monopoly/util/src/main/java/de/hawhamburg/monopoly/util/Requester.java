package de.hawhamburg.monopoly.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Ole on 24.11.2015.
 */
public class Requester {

    public static final String APPLICATION_JSON = "application/json";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String GET = "GET";
    public static final String PUT = "PUT";

    private Requester() {

    }

    public static String sendGetRequest(String resourceUrl) throws IOException {
        URL url = new URL(resourceUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setDoInput(true);
        con.setRequestMethod(GET);
        con.setRequestProperty(CONTENT_TYPE, APPLICATION_JSON);
        BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

        String line = null;
        StringBuffer buffer = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        return buffer.toString();
    }
}
