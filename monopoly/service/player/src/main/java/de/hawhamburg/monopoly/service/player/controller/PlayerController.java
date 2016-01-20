package de.hawhamburg.monopoly.service.player.controller;

import de.hawhamburg.monopoly.service.player.model.Event;
import de.hawhamburg.monopoly.service.player.model.Player;
import de.hawhamburg.monopoly.service.player.service.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOG = LoggerFactory.getLogger(PlayerController.class);

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
    public void notifyPlayerTurn(HttpServletRequest request, HttpServletResponse
            response){
        Player player = playerService.getPlayer();
        System.out.println("Your Turn!");
        LOG.info("Player "+player.getName()+" was received, a Turn event.");
        //TODO Zug anzeigen

    }

    @RequestMapping(value = "/event", method = RequestMethod.POST)
    public void notifyPlayerEvent(@RequestBody final Event[] events, HttpServletRequest request, HttpServletResponse
            response){
        for(Event e : events) {
            System.out.println(e.getName());
        }
    }

}
