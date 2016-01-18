package de.hawhamburg.monopoly.service.boards.service;

import de.hawhamburg.monopoly.service.boards.exception.EntityDoesNotExistException;
import de.hawhamburg.monopoly.service.boards.model.Board;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * Created by Marcus Jenz on 24.11.15.
 *
 * @author Marcus Jenz
 */
@Service
public class BoardsRegistry {

    private Map<Integer, Board> boards;

    @PostConstruct
    public void init() {
        boards = new HashMap<>();
    }

    public Board addBoard(Board board) {
        if(boards.containsKey(board.getGameId()))
            return  boards.get(board.getGameId());
        boards.put(board.getGameId(), board);
        return board;
    }

    public List<Board> getBoards() {
        return new ArrayList<>(this.boards.values());
    }
    public Board getBoard(int gameId) throws EntityDoesNotExistException {
        if(!boards.containsKey(gameId))
            throw new EntityDoesNotExistException();
        return this.boards.get(gameId);
    }

    public Board deleteBoard(int gameId){
        return boards.remove(gameId);
    }
}
