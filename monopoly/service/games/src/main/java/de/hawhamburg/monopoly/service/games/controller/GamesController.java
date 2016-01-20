package de.hawhamburg.monopoly.service.games.controller;

import de.hawhamburg.monopoly.service.games.model.Game;
import de.hawhamburg.monopoly.service.games.model.Player;
import de.hawhamburg.monopoly.service.games.model.wrapper.Games;
import de.hawhamburg.monopoly.service.games.service.GamesService;
import de.hawhamburg.monopoly.util.Components;
import de.hawhamburg.services.service.ServicesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import java.io.IOException;
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

    private static final Logger LOG = LoggerFactory.getLogger(GamesController.class);

    @Autowired
    private ServicesService services;

    @Autowired
    private GamesService gamesService;

    @RequestMapping(method = RequestMethod.POST)
    public Game createGame(HttpServletRequest request, HttpServletResponse response, @RequestBody final Components comp) {
        LOG.info("got a request to post method of createGame");
        Game newGame;
//        if(comp == null) {
        newGame = gamesService.createNewGame(services);
//        }else{
//            newGame = gamesService.createNewGame(comp);
//        }
        if (newGame == null) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
        } else {
            gamesService.createBoard(newGame);
            response.setStatus(HttpServletResponse.SC_CREATED);
            response.setHeader("Location",newGame.getUri());
        }
        return newGame;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Games games(HttpServletRequest request, HttpServletResponse response) {
        return Games.builder()
                .withGames(gamesService.getGames())
                .build();
    }

    @RequestMapping(value = "/{gameId}", method = RequestMethod.GET)
    public Game game(@PathVariable final int gameId, HttpServletRequest request, HttpServletResponse response) {
        //OF TODO implement
        return null;
    }

    @RequestMapping(value = "/{gameId}/players", method = RequestMethod.GET)
    public List<Player> players(@PathVariable final String gameId, HttpServletRequest request, HttpServletResponse
            response) {
        return gamesService.findGame(gameId).getPlayers();
    }

    @RequestMapping(value = "/{gameId}/players/{playerId}", method = RequestMethod.GET)
    public Player getPlayer(@PathVariable final int gameId, @PathVariable final int playerId, HttpServletRequest
            request, HttpServletResponse response) {
        //OF TODO implement
        return null;
    }

    @RequestMapping(value = "/{gameId}/players/{playerId}", method = RequestMethod.PUT)
    public void joinGame(@PathVariable final String gameId, @PathVariable final String playerId,
                         @RequestParam final String name, @RequestParam final String uri,
                         HttpServletRequest request, HttpServletResponse response) throws
            IOException {
        LOG.info("Player with id "+playerId+" trying to join game with id "+ gameId);
        Game game = gamesService.findGame(gameId);
        LOG.info("Player name: "+name+" with uri: "+uri);
        Player player = gamesService.joinGame(game, playerId, name, uri);
        LOG.info("Player joined");
        if (player != null) {
            gamesService.addPlayerToBoard(game, player);
            response.setStatus(HttpServletResponse.SC_OK);
            response.setHeader("Location",player.getUri());
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
     *
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
        if (game == null) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            LOG.info("Game with GameId " + gameId + " does not exsist, but was requested in " + request
                    .getPathInfo());
            return false;
        }
        Player player = game.getPlayer(playerId);
        if (player == null) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            LOG.info("Player with with PlayerId " + playerId + " in Game with  GameId " + gameId + " does " +
                    "not exsist, but was requested in " + request.getPathInfo());
            return false;
        }
        return player.isReady();
    }

    /**
     * End Turn
     *
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
     *
     * @param gameId
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/{gameId}/players/turn", method = RequestMethod.GET)
    public Player playersTurn(@PathVariable final String gameId, HttpServletRequest request, HttpServletResponse
            response) {
        Game game = gamesService.findGame(gameId);
        if (game == null) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            LOG.info("Game with GameId " + gameId + " does not exsist, but was requested in " + request
                    .getPathInfo());
            return null;
        }
        Player player = gamesService.findPlayerByTurnOrder(game, game.getActiveTurnOrder());
        if (player == null) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            LOG.info("No active Player was found. " + request.getPathInfo());
        }
        return player;
    }

    /**
     * Try to obtain Mutex
     *
     * @param gameId
     * @param player
     * @param request
     * @param response
     */
    @RequestMapping(value = "/{gameId}/players/turn", method = RequestMethod.PUT)
    public void aquireTurn(@PathVariable final String gameId, @RequestBody final Player player,
                           HttpServletRequest request, HttpServletResponse response) {
        Game game = gamesService.findGame(gameId);
        if (game == null) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            LOG.info("Game with GameId " + gameId + " does not exsist, but was requested in " + request
                    .getPathInfo());
            return;
        }
        gamesService.obtainPlayerMutex(game);
    }

    /**
     * Give away Mutex
     *
     * @param gameId
     * @param request
     * @param response
     */
    @RequestMapping(value = "/{gameId}/players/turn", method = RequestMethod.DELETE)
    public void releaseTurn(@PathVariable final String gameId, HttpServletRequest request, HttpServletResponse
            response) {
        Game game = gamesService.findGame(gameId);
        if (game == null) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            LOG.info("Game with GameId " + gameId + " does not exsist, but was requested in " + request
                    .getPathInfo());
            return;
        }
        gamesService.dropPlayerMutex(game);
    }
}
