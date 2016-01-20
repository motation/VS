package de.hawhamburg.monopoly.service.events.service;

import de.hawhamburg.monopoly.service.events.model.Event;
import de.hawhamburg.monopoly.service.events.model.Subscription;
import de.hawhamburg.monopoly.util.RelaxedSSLValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Ole on 18.01.2016.
 */
@Service
public class EventsService {
    private List<Event> events;

    private List<Subscription> subscriptions;

    private final String URI =  "https://vs-docker.informatik.haw-hamburg.de/ports/16314/events";

    @Autowired
    private RestTemplate restTemplate;

    @PostConstruct
    private void init(){
        events = new ArrayList<>();
        subscriptions = new ArrayList<>();
    }

    public String addEvent(Event event){
        event.setUri(URI+"/"+this.events.size());
        if(!this.events.add(event)){
            event.setUri("");
        }
        return event.getUri();
    }

    public void notifySubscriber(String gameid){
        for(Subscription subscription : subscriptions){
            if (subscription.getGameid().equals(gameid)) {
                sendNotification(subscription);
            }
        }
    }

    private void sendNotification(Subscription subscription){
        RelaxedSSLValidation.useRelaxedSSLValidation();
        String url = subscription.getUri();
        Event[] events = this.events.toArray(new Event[this.events.size()]);
        restTemplate.postForEntity(url,events,Event[].class);
    }


    public boolean addSubcriber(Subscription subscription) {
        return subscriptions.add(subscription);
    }
}
