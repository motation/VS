package de.hawhamburg.monopoly.service.boards.controller;

import de.hawhamburg.monopoly.service.boards.exception.EntityDoesNotExistException;
import de.hawhamburg.monopoly.service.boards.exception.PlayerNotReadyException;
import de.hawhamburg.monopoly.service.boards.model.Board;
import de.hawhamburg.monopoly.service.boards.model.Place;
import de.hawhamburg.monopoly.service.boards.model.Player;
import de.hawhamburg.monopoly.service.boards.model.wrapper.Rolls;
import de.hawhamburg.monopoly.service.boards.service.BoardsService;
import de.hawhamburg.monopoly.service.dice.exception.InvalidRollException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import de.hawhamburg.monopoly.service.boards.model.Roll;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by unknown on 31.10.15.
 */
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
@RequestMapping(value = "/boards")
@RestController
public class BoardsController {
    @Autowired
    private BoardsService boardsService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Board> getAllBoards(HttpServletRequest request, HttpServletResponse response){
        return boardsService.getBoards();
    }

    @RequestMapping(value = "/{gameId}", method = RequestMethod.GET)
    public List<Place> getAllPlaces(){
        //TODO
        /*
        {
  "fields":[
    {"place": "/boards/42/places/0" ,"players":[]},
    {"place": "/boards/42/places/1" ,"players":[]},
    {"place": "/boards/42/places/2" ,"players":[]},
    {"place": "/boards/42/places/3" ,"players":[]},
    {"place":{"name":"Einkommensteuer"},"players":[{"id":"Mario","place":"/boards/42/places/2", "position":4}]}
  ],
  "positions": { "Mario":4 }
}

         */
        return new ArrayList<>();
    }

    @RequestMapping(value = "/{gameId}", method = RequestMethod.PUT)
    public boolean existsGame(int gameId, HttpServletRequest request, HttpServletResponse response){
        try {
            boardsService.getBoard(gameId);
        } catch (EntityDoesNotExistException e) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            return false;
        }
        return true;
    }

    @RequestMapping(value="/boards/{gameId}", method = RequestMethod.DELETE)
    public boolean deleteGame(@PathVariable final int gameId, HttpServletRequest request, HttpServletResponse response){
        return boardsService.deleteBoard(gameId);
    }

    @RequestMapping(value="/boards/{gameId}/players", method = RequestMethod.GET)
    public List<Player> getAllPlayers(int gameId,HttpServletRequest request, HttpServletResponse response){
        try {
            return boardsService.getBoard(gameId).getPlayers();
        } catch (EntityDoesNotExistException e) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            return new ArrayList<>();
        }
    }

    @RequestMapping(value = "/boards/{gameId}/players/{playerId}", method = RequestMethod.PUT)
    public boolean addPlayer(int gameId, String playerId,HttpServletRequest request, HttpServletResponse response){
        try {
            boardsService.getPlayerPosition(gameId, playerId);
            return true;
        } catch (EntityDoesNotExistException e) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            return false;
        }
    }

    @RequestMapping(value = "/boards/{gameId}/players/{playerId}", method = RequestMethod.DELETE)
    public boolean removePlayer(int gameId, String playerId,HttpServletRequest request, HttpServletResponse response){
        try {
            boardsService.removePlayer(gameId,playerId);
        } catch (EntityDoesNotExistException e) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            return false;
        }
        return true;
    }

    @RequestMapping(value = "/games/{gameId}/players/{playerId}", method = RequestMethod.GET)
    public Player getPlayer(@PathVariable final int gameId, @PathVariable final String playerId, HttpServletRequest request, HttpServletResponse response){
        try {
            return boardsService.getPlayer(gameId, playerId);
//            return boardsService.getPlayerPosition(gameId, playerId);
        } catch (EntityDoesNotExistException e) {
            System.out.println("Game with GameId "+gameId+ " does not exsist, but was requested in "+ request.getPathInfo());
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            return null;
        }
    }


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
    public boolean postRoll(@PathVariable final int gameId, @PathVariable final String playerId, @RequestBody final Rolls roll
            , HttpServletRequest request, HttpServletResponse response){

        try {
            boardsService.movePlayer(gameId,playerId,roll.getRoll1());
            boardsService.movePlayer(gameId,playerId,roll.getRoll2());
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


    @RequestMapping(value = "/games/{gameId}/places", method = RequestMethod.GET)
    public List<Place> getPlaces(@PathVariable final int gameId, HttpServletRequest request, HttpServletResponse response){
        try {
            return boardsService.getBoard(gameId).getPlaces();
//            return boardsService.getPlayerPosition(gameId, playerId);
        } catch (EntityDoesNotExistException e) {
            System.out.println("Game with GameId "+gameId+ " does not exsist, but was requested in "+ request.getPathInfo());
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            return new ArrayList<>();
        }
    }

    @RequestMapping(value = "/games/{gameId}/places/{placeId}", method = RequestMethod.PUT)
    public Place addPlace(@PathVariable final int gameId, final String placeName, @RequestBody final Place place, HttpServletRequest request, HttpServletResponse response){
        try {
            return boardsService.getBoard(gameId).addPlace(place);
//            return boardsService.getPlayerPosition(gameId, playerId);
        } catch (EntityDoesNotExistException e) {
            System.out.println("Game with GameId "+gameId+ " does not exsist, but was requested in "+ request.getPathInfo());
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            return null;
        }
    }



}
