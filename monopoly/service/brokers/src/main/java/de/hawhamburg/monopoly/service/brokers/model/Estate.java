package de.hawhamburg.monopoly.service.brokers.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ole on 18.01.2016.
 */
public class Estate {
    // uri to the place on the board
    private String place;

    // the uri to the owner resource of the estate
    private String owner;

    // The value of the place, i.e. for how much it may be bought or sold
    private int value;

    // Rent at current level
    private List<Integer> rent;

    // Cost for house upgrade
    private List<Integer> cost;

    // amount of houses set on the estate
    private int houses;

    // the uri to the visit resource
    private String visit;

    //the uri to the hypocredit of the estate
    private String hypocredit;

    public Estate() {
        rent = new ArrayList<>();
        cost = new ArrayList<>();
    }

    public String getPlace() {
        return place;
    }

    public String getOwner() {
        return owner;
    }

    public int getValue() {
        return value;
    }

    public List<Integer> getRent() {
        return rent;
    }

    public List<Integer> getCost() {
        return cost;
    }

    public int getHouses() {
        return houses;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setRent(List<Integer> rent) {
        this.rent = rent;
    }

    public void setCost(List<Integer> cost) {
        this.cost = cost;
    }

    public void setHouses(int houses) {
        this.houses = houses;
    }
}
