package de.hawhamburg.monopoly.service.brokers.controller;

import de.hawhamburg.monopoly.service.brokers.service.BrokersService;
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
@RestController
public class BrokersController {
    @Autowired
    private BrokersService brokersService;

    @RequestMapping(value = "/brokers/{gameid}", method = RequestMethod.PUT)
    public void createBrokerForGame(@PathVariable final String gameid){

    }

    @RequestMapping(value="/brokers/{gameid}/places/{placeid}",method = RequestMethod.PUT)
    public void registerAvailableProperties(@PathVariable final String gameid, @PathVariable final int placeid){

    }

    @RequestMapping(value="/brokers/{gameid}/places/{placeid}/visit/{playerid}",method = RequestMethod.POST)
    public void registerPlayer(@PathVariable final String gameid, @PathVariable final int placeid,
                               @PathVariable final String playerid){

    }
    @RequestMapping(value="/brokers/{gameid}/places/{placeid}/owner",method = RequestMethod.POST)
    public void buyProperty(@PathVariable final String gameid, @PathVariable final int placeid){

    }

}
