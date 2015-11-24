package de.hawhamburg.monopoly.service.boards.model;

import de.hawhamburg.monopoly.service.dice.model.Roll;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ole on 15.11.2015.
 */
public class Board {

    Map<Integer, Integer> players;
    public final static int PRISON = 3;

    public Board(){
        players = new HashMap<>();
    }

    public boolean addPlayer(int id){
        return true;
    }

    public int getPosition(int playerId) {
        if(players.containsKey(playerId)){
            return players.get(playerId);
        }
            players.put(playerId, 0);
            return 0;
    }

    public int moveByRoll(int playerId, int roll) {
        int prevPosition = getPosition(playerId);
        return setPosition(playerId, prevPosition+roll);
    }

    public int setPosition(int playerId, int position){
        players.put(playerId, position);
        return position;
    }

    public int moveToPrison(int playerId) {
        players.put(playerId, PRISON);
        return PRISON;
    }
}
