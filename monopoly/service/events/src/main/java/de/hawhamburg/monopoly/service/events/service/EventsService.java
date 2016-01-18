package de.hawhamburg.monopoly.service.events.service;

import de.hawhamburg.monopoly.service.events.model.Event;
import de.hawhamburg.monopoly.service.events.model.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ole on 18.01.2016.
 */
@Service
public class EventsService {
    private List<Event> events;

    private List<Subscription> subscriptions;

    @Autowired
    private RestTemplate restTemplate;

    @PostConstruct
    private void init(){
        events = new ArrayList<>();
        subscriptions = new ArrayList<>();
    }

    public boolean addEvent(Event event){
        return this.events.add(event);
    }

    public void notifySubscriber(String gameid){
        for(Subscription subscription : subscriptions){
            if (subscription.getGameid().equals(gameid)) {
                sendNotification(subscription);
            }
        }
    }

    private void sendNotification(Subscription subscription){
        String url = subscription.getUri();
        Event[] events = new Event[1];
        events[0] = subscription.getEvent();
        restTemplate.postForEntity(url,events,Event[].class);
    }


    public boolean addSubcriber(Subscription subscription) {
        return subscriptions.add(subscription);
    }
}
