package de.hawhamburg.monopoly.service.boards.controller;

import de.hawhamburg.monopoly.service.boards.exception.EntityDoesNotExistException;
import de.hawhamburg.monopoly.service.boards.exception.PlayerNotReadyException;
import de.hawhamburg.monopoly.service.boards.model.Board;
import de.hawhamburg.monopoly.service.boards.service.BoardsService;
import de.hawhamburg.monopoly.service.dice.exception.InvalidRollException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import de.hawhamburg.monopoly.service.dice.model.Roll;

import java.io.IOException;


/**
 * Created by unknown on 31.10.15.
 */
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
@RestController
public class BoardsController {
    @Autowired
    private BoardsService boardsService;

    /**
     * Der Client uebergibt einen Würfelwurf.
     * @param gameId
     * @param playerId
     * @param roll
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/boards/{gameId}/players/{playerId}/roll", method = RequestMethod.POST)
    public boolean postRoll(@PathVariable final int gameId, @PathVariable final int playerId, @RequestParam("roll") Roll roll
            , HttpServletRequest request, HttpServletResponse response){

        try {
            boardsService.movePlayer(gameId,playerId,roll);
        } catch (InvalidRollException e) {
            System.out.println("Player with Id "+ playerId+ " wanted to move, but sent an invalid Roll");
            response.setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
            return false;
        } catch (PlayerNotReadyException e) {
            System.out.println("Player with Id "+ playerId+ " wanted to move, but was not ready");
            response.setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (EntityDoesNotExistException e) {
            System.out.println("Game with GameId "+gameId+ " does not exsist, but was requested in "+ request.getPathInfo());
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            return false;
        }
        return true;
    }


    @RequestMapping(value = "/games/{gameId}/players/{playerId}", method = RequestMethod.GET)
    public int getPlayerPosition(@PathVariable final int gameId, @PathVariable final int playerId, HttpServletRequest request, HttpServletResponse response){
        try {
            return boardsService.getPlayerPosition(gameId, playerId);
        } catch (EntityDoesNotExistException e) {
            System.out.println("Game with GameId "+gameId+ " does not exsist, but was requested in "+ request.getPathInfo());
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            return -1;
        }
    }

    @RequestMapping(value="/games/{gameId}", method = RequestMethod.GET)
    public Board getBoardState(@PathVariable final int gameId, HttpServletRequest request, HttpServletResponse response){
        try {
            return boardsService.getBoard(gameId);
        } catch (EntityDoesNotExistException e) {
            System.out.println("Game with GameId "+gameId+ " does not exsist, but was requested in "+ request.getPathInfo());
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            return null;
        }
    }


}
