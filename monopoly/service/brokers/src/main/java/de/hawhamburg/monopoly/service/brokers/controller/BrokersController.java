package de.hawhamburg.monopoly.service.brokers.controller;

import de.hawhamburg.monopoly.service.brokers.service.BrokersService;
import org.springframework.beans.factory.annotation.Autowired;
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


}
