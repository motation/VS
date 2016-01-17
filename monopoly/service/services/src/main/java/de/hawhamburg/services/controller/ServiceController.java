package de.hawhamburg.services.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Ole on 17.01.2016.
 */
@RestController
public class ServiceController {
    private static final Logger LOG = LoggerFactory.getLogger(ServiceController.class);

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public void serviceHeartbeat(){
        LOG.info("heartbeat request");
    }

    @RequestMapping(value = "/api", method = RequestMethod.GET)
    public String api(){
        LOG.info("api request");
        return "This is the api resoruce";
    }
}
