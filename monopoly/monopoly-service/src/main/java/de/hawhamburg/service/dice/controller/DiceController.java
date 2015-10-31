package de.hawhamburg.service.dice.controller;

import de.hawhamburg.service.dice.rmi.DiceRMI;
import de.hawhamburg.service.dice.rmi.Roll;
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
public class DiceController implements DiceRMI {

    @Override
    @RequestMapping(value = "/dice", method = RequestMethod.GET)
    public Roll roll() throws RemoteException {
        return new Roll(createRandomNumber());
    }

    private int createRandomNumber(){
        Random random = new Random();
        return random.nextInt(6) + 1;
    }
}
