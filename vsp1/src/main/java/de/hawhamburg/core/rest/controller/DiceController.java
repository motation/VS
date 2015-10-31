package de.hawhamburg.core.rest.controller;

import de.hawhamburg.core.rmi.entity.Roll;
import de.hawhamburg.core.rmi.service.DiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.rmi.RemoteException;

/**
 * Created by unknown on 31.10.15.
 */
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
@RestController
@RequestMapping(value = "/dice")
public class DiceController {
    @Autowired
    private DiceService diceService;

    @RequestMapping("/roll")
    public Roll roll() throws RemoteException {
        return diceService.roll();
    }
}
