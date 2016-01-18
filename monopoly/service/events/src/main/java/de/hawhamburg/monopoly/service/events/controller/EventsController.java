package de.hawhamburg.monopoly.service.events.controller;

import de.hawhamburg.monopoly.service.events.model.Event;
import de.hawhamburg.monopoly.service.events.model.Subscription;
import de.hawhamburg.monopoly.service.events.service.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Ole on 18.01.2016.
 */
@RestController
public class EventsController {

    @Autowired
    private EventsService eventsService;

    @RequestMapping(value = "/events", method = RequestMethod.POST)
    public void createEvent(@RequestParam final String gameid, @RequestBody final Event event,
                            HttpServletResponse response){
        if (eventsService.addEvent(event)) {
            eventsService.notifySubscriber(gameid);
            response.setStatus(HttpServletResponse.SC_CREATED);
        } else {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
        }
    }

    @RequestMapping(value = "/events/subscriptions", method = RequestMethod.POST)
    public void subscribe(@RequestBody Subscription subscription, HttpServletResponse response){
        if (eventsService.addSubcriber(subscription)) {
            response.setStatus(HttpServletResponse.SC_CREATED);
        } else {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
        }
    }


}
