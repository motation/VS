package de.hawhamburg.monopoly.service.boards.service;

import de.hawhamburg.monopoly.service.boards.exception.EntityDoesNotExistException;
import de.hawhamburg.monopoly.service.boards.model.Board;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Map<String, Board> boards;
    private static final Logger LOG = LoggerFactory.getLogger(BoardsRegistry.class);
    @PostConstruct
    public void init() {
        boards = new HashMap<>();
    }

    public Board addBoard(Board board) {
        LOG.info("Adding Board to registry  ");
        if(boards.containsKey(board.getGameId()))
            return  boards.get(board.getGameId());
        boards.put(board.getGameId(), board);
        return board;
    }

    public List<Board> getBoards() {
        return new ArrayList<>(this.boards.values());
    }
    public Board getBoard(String gameId) throws EntityDoesNotExistException {
        if(!boards.containsKey(gameId))
            throw new EntityDoesNotExistException();
        return this.boards.get(gameId);
    }

    public Board deleteBoard(String gameId){
        return boards.remove(gameId);
    }
}
