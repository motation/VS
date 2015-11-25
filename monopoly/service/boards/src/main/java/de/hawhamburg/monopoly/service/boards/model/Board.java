package de.hawhamburg.monopoly.service.boards.model;

import de.hawhamburg.monopoly.service.dice.model.Roll;
import de.hawhamburg.monopoly.service.boards.model.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ole on 15.11.2015.
 */
public class Board {

    private Map<String, Player> players;
    public final static int PRISON = 3;

    public Board(){
        players = new HashMap<>();
    }

    public int getPosition(String playerId) {
        if(players.containsKey(playerId)){
            return players.get(playerId).getPosition();
        }
        Player player = new Player(playerId);
            players.put(playerId, player);
            return player.getPosition();
    }

    public int moveByRoll(String playerId, int roll) {
        Player player = players.get(playerId);
        //TODO was tun wenn der Spieler nicht existiert?
        player.setPosition(player.getPosition()+roll);
//        int prevPosition = player.getPosition();
        return player.getPosition();
    }

    public int moveToPrison(String playerId) {
        Player player = players.get(playerId);
        player.setPosition(PRISON);
        return PRISON;
    }
}
