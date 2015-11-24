package de.hawhamburg.monopoly.service.boards.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.hawhamburg.monopoly.service.boards.exception.EntityDoesNotExistException;
import de.hawhamburg.monopoly.service.boards.exception.PlayerNotReadyException;
import de.hawhamburg.monopoly.service.boards.model.Board;
import de.hawhamburg.monopoly.service.dice.exception.InvalidRollException;
import de.hawhamburg.monopoly.service.dice.model.Roll;
import de.hawhamburg.monopoly.service.player.model.Player;
import de.hawhamburg.monopoly.util.Requester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by Ole on 04.11.2015.
 */
@Service
public class BoardsService {

    @Autowired
    BoardsRegistry registry;

    public Board createNewBoard(int gameId, Board board){ return registry.addBoard(gameId, board);}

    public int getPlayerPosition(int gameId, int playerId) throws EntityDoesNotExistException {
        return registry.getBoard(gameId).getPosition(playerId);

    }

    public int movePlayer(int gameId, int playerId, Roll roll) throws InvalidRollException, PlayerNotReadyException, IOException, EntityDoesNotExistException {
        if (!roll.isValid()) {
            throw new InvalidRollException();
        }
        if(!isPlayerReady(gameId, playerId)){
            throw new PlayerNotReadyException();
        }
            return registry.getBoard(gameId).moveByRoll(playerId, roll.getNumber());

    }

    public int moveToPrison(int gameId, int playerId) throws PlayerNotReadyException, IOException, EntityDoesNotExistException {
        if(!isPlayerReady(gameId, playerId)){
            throw new PlayerNotReadyException();
        }
        return registry.getBoard(gameId).moveToPrison(playerId);
    }

    public Board getBoard(int gameId) throws EntityDoesNotExistException {
        return registry.getBoard(gameId);
    }

    private boolean isPlayerReady(int gameId, int playerId) throws IOException {
        String json = Requester.sendGetRequest("/games/" + gameId + "/turn");
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Player p = gson.fromJson(json,Player.class);
        //TODO Player aus response zusammenbauen.
        return p.getId().equals(Integer.toString(playerId));
    }
}
