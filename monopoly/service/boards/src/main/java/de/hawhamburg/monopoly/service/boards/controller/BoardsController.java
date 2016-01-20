package de.hawhamburg.monopoly.service.boards.controller;

import de.hawhamburg.monopoly.service.boards.exception.EntityDoesNotExistException;
import de.hawhamburg.monopoly.service.boards.exception.InvalidRollException;
import de.hawhamburg.monopoly.service.boards.exception.PlayerNotReadyException;
import de.hawhamburg.monopoly.service.boards.model.*;
import de.hawhamburg.monopoly.service.boards.model.wrapper.Rolls;
import de.hawhamburg.monopoly.service.boards.service.BoardsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

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

    private static final Logger LOG = LoggerFactory.getLogger(BoardsController.class);


    @RequestMapping(method = RequestMethod.GET)
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
        return new ArrayList<Place>();
    }



    @RequestMapping(value="/{gameId}", method = RequestMethod.DELETE)
    public boolean deleteGame(@PathVariable final String gameId, HttpServletRequest request, HttpServletResponse response){
        return boardsService.deleteBoard(gameId);
    }

    @RequestMapping(value="/{gameId}/players", method = RequestMethod.GET)
    public List<Player> getAllPlayers(@PathVariable final String gameId,HttpServletRequest request, HttpServletResponse response){
        try {
            return boardsService.getBoard(gameId).getPlayers();
        } catch (EntityDoesNotExistException e) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            return new ArrayList<>();
        }
    }

    @RequestMapping(value = "/{gameId}/players/{playerId}", method = RequestMethod.PUT)
    public boolean addPlayer(@PathVariable final String gameId, @PathVariable final String playerId,HttpServletRequest request, HttpServletResponse response){
        LOG.info("Got Request to join Gameid "+ gameId + " for playerid "+ playerId);
        try {
            boardsService.getPlayerPosition(gameId, playerId);
            return true;
        } catch (EntityDoesNotExistException e) {
            LOG.error("Board with id: "+gameId+"does not Exists");
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            return false;
        }
    }

    @RequestMapping(value = "/{gameId}/players/{playerId}", method = RequestMethod.DELETE)
    public boolean removePlayer(@PathVariable final String  gameId, @PathVariable final String  playerId,HttpServletRequest request, HttpServletResponse response){
        try {
            boardsService.removePlayer(gameId,playerId);
        } catch (EntityDoesNotExistException e) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            return false;
        }
        return true;
    }

    @RequestMapping(value = "/games/{gameId}/players/{playerId}", method = RequestMethod.GET)
    public Player getPlayer(@PathVariable final String gameId, @PathVariable final String playerId, HttpServletRequest request, HttpServletResponse response){
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
    @RequestMapping(value = "/{gameId}/players/{playerId}/roll", method = RequestMethod.POST)
    public boolean postRoll(@PathVariable final String gameId, @PathVariable final String playerId, @RequestBody final Rolls roll
            , HttpServletRequest request, HttpServletResponse response){

        try {
            boardsService.movePlayer(gameId,playerId,roll.getRoll1());
            boardsService.movePlayer(gameId,playerId,roll.getRoll2());
        } catch (PlayerNotReadyException e) {
            LOG.warn("Player with Id "+ playerId+ " wanted to move, but was not ready");
            response.setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (EntityDoesNotExistException e) {
            LOG.warn("Game with GameId "+gameId+ " does not exsist, but was requested in "+ request.getPathInfo());
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            return false;
        } catch (InvalidRollException e) {
            LOG.warn("Game with GameId "+gameId+ " got an Invalid Roll by player " +playerId+ " Pathinfo: "+ request.getPathInfo());
            System.out.println("Game with GameId "+gameId+ " got an Invalid Roll by player " +playerId+ " Pathinfo: "+ request.getPathInfo());
        }
        return true;
    }


    @RequestMapping(value = "/games/{gameId}/places", method = RequestMethod.GET)
    public List<Place> getPlaces(@PathVariable final String gameId, HttpServletRequest request, HttpServletResponse response){
        try {
            return boardsService.getBoard(gameId).getPlaces();
//            return boardsService.getPlayerPosition(gameId, playerId);
        } catch (EntityDoesNotExistException e) {
            LOG.warn("Game with GameId "+gameId+ " does not exsist, but was requested in "+ request.getPathInfo());
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            return new ArrayList<>();
        }
    }

    @RequestMapping(value = "/{gameId}/places/{placeId}", method = RequestMethod.PUT)
    public Place addPlace(@PathVariable final String gameId, @PathVariable final String placeId, @RequestBody final Place place, HttpServletRequest request, HttpServletResponse response){
        LOG.info("Trying to create place: "+ placeId + " for game "+ gameId+ " Place entity "+place.getUri());
        try {
            return boardsService.getBoard(gameId).addPlace(place);
        } catch (EntityDoesNotExistException e) {
            LOG.warn("Game with GameId "+gameId+ " does not exsist, but was requested in "+ request.getPathInfo());
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            return null;
        }
    }

     @RequestMapping(value = "/{gameId}", method = RequestMethod.PUT)
    public Board createBoard(@PathVariable final String gameId, @RequestBody Game game, HttpServletRequest request, HttpServletResponse response){
         LOG.info("TRying to create Board for Game: "+gameId);
         Board board = boardsService.createNewBoard(game);
         LOG.info("Board created");
         return board;
     }



}
