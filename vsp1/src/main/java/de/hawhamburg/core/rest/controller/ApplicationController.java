package de.hawhamburg.core.rest.controller;

import de.hawhamburg.core.rmi.entity.Roll;
import de.hawhamburg.core.rmi.service.DiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import java.rmi.RemoteException;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by unknown on 31.10.15.
 */
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
@RestController
public class ApplicationController {
    @RequestMapping(value = "/")
    public String startPath(){
        return "MonopolyApplication";
    }
}
