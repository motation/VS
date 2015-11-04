package de.hawhamburg.service.dice.controller;

import de.hawhamburg.service.dice.rmi.DiceRMI;
import de.hawhamburg.service.dice.rmi.Roll;
import de.hawhamburg.service.dice.service.DiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import java.rmi.RemoteException;
import java.util.Random;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by unknown on 31.10.15.
 */
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
@RestController
public class DiceController {

    @Autowired
    private DiceService diceService;

    @RequestMapping(value = "/dice", method = RequestMethod.GET)
    public Roll roll() throws RemoteException {
        return diceService.roll();
    }
}
