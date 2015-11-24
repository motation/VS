package de.hawhamburg.monopoly.service.player.controller;

import de.hawhamburg.monopoly.service.player.model.Player;
import de.hawhamburg.monopoly.service.player.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

}
