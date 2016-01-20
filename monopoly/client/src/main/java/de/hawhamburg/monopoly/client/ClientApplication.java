package de.hawhamburg.monopoly.client;

import java.io.IOException;

/**
 * Created by Ole on 25.11.2015.
 */
public class ClientApplication {
    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.createGame("123");
//        client.createGame("1001");
//        client.createPlayer("1","Peter");
//        client.getGames();
//        client.joinGame("1001","1","Peter");
//        client.roll();
    }
}
