package de.hawhamburg.monopoly.service.brokers.service;

import de.hawhamburg.monopoly.service.brokers.model.Broker;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ole on 04.11.2015.
 */
@Service
public class BrokersService {
    private Map<String, Broker> brokers;

    @PostConstruct
    private void init() {
        brokers = new HashMap<>();
    }

    public boolean addBroker(String gameid) {
        return (brokers.putIfAbsent(gameid, new Broker()) != null);
    }

    public Broker getBroker(String gameid){
        return brokers.get(gameid);
    }




}
