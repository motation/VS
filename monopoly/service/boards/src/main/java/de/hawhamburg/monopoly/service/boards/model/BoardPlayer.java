package de.hawhamburg.monopoly.service.boards.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcus Jenz on 20.01.16.
 *
 * @author Marcus Jenz
 */
public class BoardPlayer {

    private Player player;
    private Board board;

    public BoardPlayer() {
    }

    public BoardPlayer(Player player, Board board) {
        this.player = player;
        this.board = board;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public String getId(){return player.getId();}
    public String getUri(){return player.getUri();}
    public String getPlace() {return player.getPlace().getUri();}
    public int getPosition(){return player.getPosition();}
    public String getRoll() {return "";/*MJ TODO */}
    public String getMove() {return "";/*MJ TODO */}
}