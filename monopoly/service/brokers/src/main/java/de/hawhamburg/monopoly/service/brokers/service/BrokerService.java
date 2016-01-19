package de.hawhamburg.monopoly.service.brokers.service;

import de.hawhamburg.monopoly.service.brokers.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * Created by Ole on 04.11.2015.
 */
@Service
public class BrokerService {
    private Map<String, Broker> brokers;

    @Autowired
    private RestTemplate restTemplate;

    @PostConstruct
    private void init() {
        brokers = new HashMap<>();
    }

    public boolean createBrokerForGame(String gameid, Game game) {
        boolean brokerCreated = addBroker(gameid);
        boolean gameAdded = addGame(gameid, game);
        return brokerCreated && gameAdded;
    }

    private boolean addGame(String gameid, Game game) {
        Game registeredGame = brokers.get(gameid).getGame();
        boolean gameAdded = false;
        if (registeredGame == null) {
            brokers.get(gameid).setGame(game);
            gameAdded = true;
        }
        return gameAdded;
    }

    private boolean addBroker(String gameid) {
        return (brokers.put(gameid, new Broker()) == null);
    }

    private Broker getBroker(String gameid) {
        return brokers.get(gameid);
    }

    // returns true if created
    // returns false if already present
    public boolean registerProperties(Estate estate, String gameid, int placeid) {
        boolean created = false;
        Broker broker = getBroker(gameid);
        if (!broker.getEstates().containsKey(placeid)) {
            created = (broker.getEstates().put(placeid, estate)) == null;
        }
        return created;
    }

    public List<Event> playerVisitsPlace(String gameid, int placeid, String playerid) {
        List<Event> resultedEvents = new ArrayList<>();
        Broker broker = getBroker(gameid);
        Estate estate = broker.getEstates().get(placeid);
        Player owner = broker.getOwners().get(placeid);
        if (owner != null && !owner.getId().equals(playerid)) {

            //OF you dont own this estate pay for it(rent)
            int rentLevel = estate.getRent().size() - 1;
            int rentCost = estate.getRent().get(rentLevel);
            String bankUri = broker.getGame().getComponents().getBank();

            //OF TODO what is from and what is to?!
            String from = "from";
            String to = "to";
            int amount = rentCost;

            bankUri += "/" + gameid + "/transfer/from/" + from + "/to/" + to + "/" + amount;

            HttpHeaders headers = new HttpHeaders();
            HttpEntity entity = new HttpEntity(headers);

            try {
                ResponseEntity<Event[]> responseEntity = null;
                responseEntity = restTemplate.exchange(bankUri, HttpMethod.POST, entity, Event[].class);
                if (responseEntity.getStatusCode().equals(HttpStatus.CREATED)) {
                    resultedEvents = Arrays.asList(responseEntity.getBody());
                } else if (responseEntity.getStatusCode().equals(HttpStatus.FORBIDDEN)) {
                    //OF TODO create events for forbidden transfer...
                }
            } catch (RestClientException e) {
                e.printStackTrace();
            }
        } else {
            // OF TODO create events for not paying because no one owns this property
        }
        return resultedEvents;
    }


    public List<Event> buyProperty(String gameid, int placeid,Player player) {
        //OF TODO IMPLEMENT!

        List<Event> resultedEvents = new ArrayList<>();

        // buy this place, but it will fail if it is not for sale
        Broker broker = brokers.get(gameid);
        Estate estate = broker.getEstates().get(placeid);
        Game game = broker.getGame();

        if(estate.getOwner() == null){
            //OF TODO it is for sale
            // OF TODO is it for sale if another user owns this property?!
            String bankUri = game.getComponents().getBank();
            bankUri += "";


        } else {
            //OF TODO not for sale!
            // create events
        }
        return resultedEvents;
    }
}
