package de.hawhamburg.monopoly.service.boards.service;

import de.hawhamburg.monopoly.service.boards.exception.EntityDoesNotExistException;
import de.hawhamburg.monopoly.service.boards.exception.PlayerNotReadyException;
import de.hawhamburg.monopoly.service.boards.model.Board;
import de.hawhamburg.monopoly.service.boards.model.Game;
import de.hawhamburg.monopoly.service.boards.model.Player;
import de.hawhamburg.monopoly.service.boards.exception.InvalidRollException;
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

    public Board createNewBoard(Game game){ return registry.addBoard( new Board(game));}

    public int getPlayerPosition(String gameId, String playerId) throws EntityDoesNotExistException {
        return registry.getBoard(gameId).getPosition(playerId);

    }

    public int movePlayer(String gameId, String playerId, Roll roll) throws InvalidRollException, PlayerNotReadyException, IOException, EntityDoesNotExistException {
            return registry.getBoard(gameId).moveByRoll(playerId, roll.getNumber());

    }

    public int moveToPrison(String gameId, String playerId) throws PlayerNotReadyException, IOException, EntityDoesNotExistException {
//        if(!isPlayerReady(gameId, playerId)){
//            throw new PlayerNotReadyException();
//        }
        return registry.getBoard(gameId).moveToPrison(playerId);
    }

    public Board getBoard(String gameId) throws EntityDoesNotExistException {
        return registry.getBoard(gameId);
    }

    public boolean deleteBoard(String gameId){
        return (registry.deleteBoard(gameId) != null);
    }


    public List<Board> getBoards() {
        return registry.getBoards();
    }

    public Player getPlayer(String gameId, String playerId) throws EntityDoesNotExistException {
        return registry.getBoard(gameId).getPlayer(playerId);
    }

    public boolean removePlayer(String gameId, String playerId) throws EntityDoesNotExistException {
        return (registry.getBoard(gameId).removePlayer(playerId) != null);
    }
}
