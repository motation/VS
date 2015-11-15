package de.hawhamburg.monopoly.service.games.controller;

import de.hawhamburg.monopoly.service.games.model.Game;
import de.hawhamburg.monopoly.service.games.service.GamesService;
import de.hawhamburg.monopoly.service.player.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import java.net.URI;
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
    public void createGame(@RequestBody final Game game, HttpServletRequest request, HttpServletResponse response) {
        //OF TODO implement
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Game> games(HttpServletRequest request, HttpServletResponse response) {
        //OF TODO implement
        return null;
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
    public void putPlayer(@PathVariable final int gameId, @PathVariable final int playerId, @RequestParam final
    String name, @RequestParam final String uri, HttpServletRequest request, HttpServletResponse response) {
        //OF TODO implement
    }

    @RequestMapping(value = "/{gameId}/players/{playerId}", method = RequestMethod.DELETE)
    public void deletePlayer(@PathVariable final int gameId, @PathVariable final int playerId, HttpServletRequest
            request, HttpServletResponse response) {
        //OF TODO implement
    }

    @RequestMapping(value = "/{gameId}/players/{playerId}/ready", method = RequestMethod.GET)
    public boolean isPlayerReady(@PathVariable final int gameId, @PathVariable final int playerId, HttpServletRequest
            request, HttpServletResponse response) {
        //OF TODO implement
        return true;
    }

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

    @RequestMapping(value = "/{gameId}/players/turn", method = RequestMethod.GET)
    public Player playersTurn(@PathVariable final int gameId, HttpServletRequest request, HttpServletResponse
            response) {
        //OF TODO implement
        return null;
    }

    @RequestMapping(value = "/{gameId}/players/turn", method = RequestMethod.PUT)
    public void aquireTurn(@PathVariable final int gameId, @RequestBody final Player player,
                           HttpServletRequest request, HttpServletResponse response) {
        //OF TODO implement
    }

    @RequestMapping(value = "/{gameId}/players/turn", method = RequestMethod.DELETE)
    public void releaseTurn(@PathVariable final int gameId, HttpServletRequest request, HttpServletResponse response) {
        //OF TODO implement
    }


}
