package de.hawhamburg.monopoly.service.brokers.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Ole on 18.01.2016.
 */
public class Broker {

    private Map<Integer, Estate> estates;
    private Map<String, Player> owners;
    private Game game;


    public Broker() {
        estates = new HashMap<>();
        owners = new HashMap<>();
    }

    public Map<Integer, Estate> getEstates() {
        return estates;
    }

    public void setEstates(Map<Integer, Estate> estates) {
        this.estates = estates;
    }

    public Map<String, Player> getOwners() {
        return owners;
    }

    public void setOwners(Map<String, Player> owners) {
        this.owners = owners;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
