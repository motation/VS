package de.hawhamburg.monopoly.service.boards.service;

import de.hawhamburg.monopoly.service.boards.exception.EntityDoesNotExistException;
import de.hawhamburg.monopoly.service.boards.exception.PlayerNotReadyException;
import de.hawhamburg.monopoly.service.boards.model.Board;
import de.hawhamburg.monopoly.service.boards.model.Player;
import de.hawhamburg.monopoly.service.dice.exception.InvalidRollException;
import de.hawhamburg.monopoly.service.boards.model.Roll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * Created by Ole on 04.11.2015.
 */
@Service
public class BoardsService {

    @Autowired
    BoardsRegistry registry;

    public Board createNewBoard(int gameId, Board board){ return registry.addBoard(gameId, board);}

    public int getPlayerPosition(int gameId, String playerId) throws EntityDoesNotExistException {
        return registry.getBoard(gameId).getPosition(playerId);

    }

    public int movePlayer(int gameId, String playerId, Roll roll) throws InvalidRollException, PlayerNotReadyException, IOException, EntityDoesNotExistException {
//        if (!roll.isValid()) {
//            throw new InvalidRollException();
//        }
//        if(!isPlayerReady(gameId, playerId)){
//            throw new PlayerNotReadyException();
//        }
            return registry.getBoard(gameId).moveByRoll(playerId, roll.getNumber());

    }

    public int moveToPrison(int gameId, String playerId) throws PlayerNotReadyException, IOException, EntityDoesNotExistException {
//        if(!isPlayerReady(gameId, playerId)){
//            throw new PlayerNotReadyException();
//        }
        return registry.getBoard(gameId).moveToPrison(playerId);
    }

    public Board getBoard(int gameId) throws EntityDoesNotExistException {
        return registry.getBoard(gameId);
    }

    public boolean deleteBoard(int gameId){
        return (registry.deleteBoard(gameId) != null);
    }

//    private boolean isPlayerReady(int gameId, int playerId) throws IOException {
//        String json = Requester.sendGetRequest("/games/" + gameId + "/turn");
//        GsonBuilder builder = new GsonBuilder();
//        Gson gson = builder.create();
//        Player p = gson.fromJson(json,Player.class);
//
//        return p.getId().equals(Integer.toString(playerId));
//    }

    public List<Board> getBoards() {
        return registry.getBoards();
    }

    public Player getPlayer(int gameId, String playerId) throws EntityDoesNotExistException {
        return registry.getBoard(gameId).getPlayer(playerId);
    }

    public boolean removePlayer(int gameId, String playerId) throws EntityDoesNotExistException {
        return (registry.getBoard(gameId).removePlayer(playerId) != null);
    }
}
