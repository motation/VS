package de.hawhamburg.monopoly.service.brokers.controller;

import de.hawhamburg.monopoly.service.brokers.model.*;
import de.hawhamburg.monopoly.service.brokers.service.BrokerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by unknown on 31.10.15.
 */
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
@RestController
public class BrokersController {
    @Autowired
    private BrokerService brokerService;

    @RequestMapping(value = "/broker/{gameid}", method = RequestMethod.PUT)
    public void createBrokerForGame(@PathVariable final String gameid,
                                    @RequestBody final Game game, HttpServletResponse response) {
        if (brokerService.createBrokerForGame(gameid, game)) {
            response.setStatus(HttpServletResponse.SC_CREATED);
            Broker broker = brokerService.getBroker(gameid);
//            response.setHeader("Location",broker.getUri());
        } else {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
        }
    }

    @RequestMapping(value = "/broker/{gameid}/places/{placeid}", method = RequestMethod.PUT)
    public void registerAvailableProperties(@PathVariable final String gameid, @PathVariable final int placeid,
                                            @RequestBody final Estate estate, HttpServletResponse response) {
        // if already present -> 200
        // if created -> 201
        if (brokerService.registerProperties(estate, gameid, placeid)) {
            response.setStatus(HttpServletResponse.SC_CREATED);
        } else {
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/broker/{gameid}/places/{placeid}/visit/{playerid}", method = RequestMethod.POST)
    public List<Event> playerVisitsPlace(@PathVariable final String gameid, @PathVariable final int placeid,
                                  @PathVariable final String playerid, HttpServletResponse response) {
        List<Event> resultedEvents = brokerService.playerVisitsPlace(gameid, placeid, playerid);
        if (resultedEvents != null) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
        }
        return resultedEvents;
    }

    @RequestMapping(value = "/broker/{gameid}/places/{placeid}/owner", method = RequestMethod.POST)
    public List<Event> buyProperty(@PathVariable final String gameid, @PathVariable final int placeid,
                                   @RequestBody final Player player, HttpServletResponse response) {
        List<Event> resultedEvents = brokerService.buyProperty(gameid,placeid,player);
        if (resultedEvents != null) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
        }
        return resultedEvents;
    }

    @RequestMapping(value = "/broker/{gameid}",method = RequestMethod.GET)
    public Broker getBrokerForGame(@PathVariable final String gameid){
        return brokerService.getBroker(gameid);
    }

}
