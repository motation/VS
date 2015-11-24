package de.hawhamburg.monopoly.service.games.controller;

import de.hawhamburg.monopoly.service.games.model.Game;
import de.hawhamburg.monopoly.service.games.model.Place;
import de.hawhamburg.monopoly.service.games.model.wrapper.Games;
import de.hawhamburg.monopoly.service.games.service.GamesService;
import de.hawhamburg.monopoly.service.player.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by unknown on 31.10.15.
 */
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
@RequestMapping(value = "/games")
@RestController
public class GamesController {
    @Autowired
    private GamesService gamesService;

    @RequestMapping(method = RequestMethod.POST)
    public Game createGame(@RequestBody final Game game, HttpServletRequest request, HttpServletResponse response) {
        Game newGame = gamesService.createNewGame(game);
        if (newGame == null) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
        } else {
            response.setStatus(HttpServletResponse.SC_CREATED);
        }
        return game;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Games games(HttpServletRequest request, HttpServletResponse response) {
        return new Games(gamesService.getGames());
    }

    @RequestMapping(value = "/{gameId}", method = RequestMethod.GET)
    public Game game(@PathVariable final int gameId, HttpServletRequest request, HttpServletResponse response) {
        //OF TODO implement
        return null;
    }

    @RequestMapping(value = "/{gameId}/players", method = RequestMethod.GET)
    public List<Player> players(@PathVariable final int gameId, HttpServletRequest request, HttpServletResponse
            response) {
        //OF TODO implement
        return null;
    }

    @RequestMapping(value = "/{gameId}/players/{playerId}", method = RequestMethod.GET)
    public Player getPlayer(@PathVariable final int gameId, @PathVariable final int playerId, HttpServletRequest
            request, HttpServletResponse response) {
        //OF TODO implement
        return null;
    }

    @RequestMapping(value = "/{gameId}/players/{playerId}", method = RequestMethod.PUT)
    public void joinGame(@PathVariable final String gameId, @PathVariable final String playerId, @RequestParam final
    String name, @RequestParam final String uri, HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean successfulJoined=gamesService.joinGame(gameId,playerId,name,uri);
        if(successfulJoined){
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
        }
    }

    @RequestMapping(value = "/{gameId}/players/{playerId}", method = RequestMethod.DELETE)
    public void deletePlayer(@PathVariable final int gameId, @PathVariable final int playerId, HttpServletRequest
            request, HttpServletResponse response) {
        //OF TODO implement
    }

    /**
     * Checks if the given Player is ready
     * @param gameId
     * @param playerId
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/{gameId}/players/{playerId}/ready", method = RequestMethod.GET)
    public boolean isPlayerReady(@PathVariable final int gameId, @PathVariable final String playerId, HttpServletRequest
            request, HttpServletResponse response) {
        Game game = gamesService.findGame(Integer.toString(gameId));
        if(game ==null){
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            System.out.println("Game with GameId "+gameId+ " does not exsist, but was requested in "+ request.getPathInfo());
            return false;
        }
        Player player = game.getPlayer(playerId);
        if (player == null){
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            System.out.println("Player with with PlayerId "+playerId+" in Game with  GameId "+gameId+ " does not exsist, but was requested in "+ request.getPathInfo());
            return false;
        }
        return player.isReady();
    }

    /**
     * End Turn
     * @param gameId
     * @param playerId
     * @param request
     * @param response
     */
    @RequestMapping(value = "/{gameId}/players/{playerId}/ready", method = RequestMethod.PUT)
    public void playerIsReady(@PathVariable final int gameId, @PathVariable final int playerId, HttpServletRequest
            request, HttpServletResponse response) {
        //OF TODO implement
    }

    @RequestMapping(value = "/{gameId}/players/current", method = RequestMethod.GET)
    public Player getCurrentActivePlayer(@PathVariable final int gameId, HttpServletRequest request,
                                         HttpServletResponse response) {
        //OF TODO implement
        return null;
    }

    /**
     * Return which player is active.
     * @param gameId
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/{gameId}/players/turn", method = RequestMethod.GET)
    public Player playersTurn(@PathVariable final String gameId, HttpServletRequest request, HttpServletResponse
            response) {
        Game game = gamesService.findGame(gameId);
        if(game ==null){
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            System.out.println("Game with GameId "+gameId+ " does not exsist, but was requested in "+ request.getPathInfo());
            return null;
        }
        Player player = gamesService.findPlayerByTurnOrder(game, game.getActiveTurnOrder());
        if (player == null){
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            System.out.println("No active Player was found. "+ request.getPathInfo());
        }
        return player;
    }

    /**
     * Try to obtain Mutex
     * @param gameId
     * @param player
     * @param request
     * @param response
     */
    @RequestMapping(value = "/{gameId}/players/turn", method = RequestMethod.PUT)
    public void aquireTurn(@PathVariable final String gameId, @RequestBody final Player player,
                           HttpServletRequest request, HttpServletResponse response) {
        Game game = gamesService.findGame(gameId);
        if(game ==null){
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            System.out.println("Game with GameId "+gameId+ " does not exsist, but was requested in "+ request.getPathInfo());
            return;
        }
        gamesService.obtainPlayerMutex(game);
    }

    /**
     * Give away Mutex
     * @param gameId
     * @param request
     * @param response
     */
    @RequestMapping(value = "/{gameId}/players/turn", method = RequestMethod.DELETE)
    public void releaseTurn(@PathVariable final String gameId, HttpServletRequest request, HttpServletResponse response) {
        Game game = gamesService.findGame(gameId);
        if(game ==null){
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            System.out.println("Game with GameId "+gameId+ " does not exsist, but was requested in "+ request.getPathInfo());
            return;
        }
        gamesService.dropPlayerMutex(game);
    }

    //OF TODO remove this dummy generator
    // just for generation...
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public void runTest(HttpServletRequest request, HttpServletResponse response) {
        List<Player> players = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            Place place = Place.builder()
                    .withName("Place " + i)
                    .build();
            Player player = Player.builder()
                    .withId("" + i)
                    .withName("John " + i)
                    .withPlace(place)
                    .withPosition(i)
                    .withReady(i % 2 == 0)
                    .withUri("http://localhost:4567/player/" + i)
                    .build();
            players.add(player);
        }
        Game game = Game.builder()
                .withGameid("10")
                .withPlayers(players)
                .withUri("http://localhost:4567/games/10")
                .build();
        createGame(game, request, response);
    }
    @RequestMapping(value = "/test/{gameId}", method = RequestMethod.GET)
    public void runTest2(@PathVariable final String gameId,HttpServletRequest request, HttpServletResponse response)
            throws
            IOException {
        joinGame(gameId,"123","Peter","http://localhost:456/player/123",request,response);
    }

}
