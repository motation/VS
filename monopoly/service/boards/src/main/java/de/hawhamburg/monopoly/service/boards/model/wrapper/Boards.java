package de.hawhamburg.monopoly.service.boards.model.wrapper;

import de.hawhamburg.monopoly.service.boards.model.Board;

import java.util.List;

/**
 * Created by Marcus Jenz on 24.11.15.
 *
 * @author Marcus Jenz
 */
public class Boards {

    private List<Board> Boards;

    public Boards(List<Board> Boards) {
        this.Boards = Boards;
    }

    public List<Board> getBoards() {
        return Boards;
    }

    public Board getBoard(int boardId){
        return this.Boards.get(boardId);
    }
}
