package de.hawhamburg.monopoly.service.player.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import de.hawhamburg.monopoly.service.player.model.*;
import de.hawhamburg.monopoly.service.player.service.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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
            response.setHeader("Location",player.getUri());
        } else {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
        }
        return player;
    }

    @RequestMapping(value = "/turn", method = RequestMethod.POST)
    public void notifyPlayerTurn(HttpServletRequest request, HttpServletResponse
            response, @RequestBody final String json){
        Player player = playerService.getPlayer();
        Gson gson = new GsonBuilder().create();
        JsonObject jsonObject = gson.fromJson( json, JsonObject.class);
        String boardPlayerURi =  jsonObject.get("onboard").getAsString();
        LOG.info("Your Turn!");
        LOG.info("Player "+player.getName()+" was received, a Turn event.");
        Roll r1 = new Roll(2);
        Roll r2 = new Roll(4);
        Rolls rolls = new Rolls();
        rolls.setRoll1(r1);
        rolls.setRoll2(r2);
        RestTemplate template = new RestTemplate();
        ResponseEntity<Place> entity = template.postForEntity(boardPlayerURi, rolls, Place.class);
        String placeUri = entity.getBody().getUri();
        ResponseEntity<String> place2Resp = template.getForEntity(placeUri, String.class);
        jsonObject = gson.fromJson( json, JsonObject.class);
        String brokerUri =  jsonObject.get("broker").getAsString();

    }

    @RequestMapping(value = "/event", method = RequestMethod.POST)
    public void notifyPlayerEvent(@RequestBody final Event[] events, HttpServletRequest request, HttpServletResponse
            response){
        for(Event e : events) {
            LOG.info(e.getName());
        }
    }

}
