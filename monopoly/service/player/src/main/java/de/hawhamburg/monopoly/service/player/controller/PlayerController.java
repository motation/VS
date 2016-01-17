package de.hawhamburg.monopoly.service.player.controller;

import de.hawhamburg.monopoly.service.player.model.Player;
import de.hawhamburg.monopoly.service.player.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by unknown on 31.10.15.
 */
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
@RequestMapping(value = "/player")
@RestController
public class PlayerController {
    @Autowired
    private PlayerService playerService;


    @RequestMapping(value="/{playerId}", method = RequestMethod.GET)
    public Player player(@PathVariable final String playerId){
        return playerService.getPlayer(playerId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Player createPlayer(@RequestBody final Player player,HttpServletRequest request, HttpServletResponse
            response){

        if(playerService.createPlayer(player)){
            response.setStatus(HttpServletResponse.SC_CREATED);
        } else {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
        }
        return player;
    }

    @RequestMapping(value = "/turn", method = RequestMethod.POST)
    public void notifyPlayerTurn(@RequestBody final Player player,HttpServletRequest request, HttpServletResponse
            response){

        //TODO Zug anzeigen

    }

    @RequestMapping(value = "/event", method = RequestMethod.POST)
    public void notifyPlayerEvent(@RequestBody final Player player,HttpServletRequest request, HttpServletResponse
            response){

        //TODO Event auspacken.
    }

}
