package de.hawhamburg.monopoly.service.brokers.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ole on 18.01.2016.
 */
public class Estate {
    private String place;
    private String owner;
    private int value;
    private List<Integer> rent;
    private List<Integer> cost;
    private int houses;

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
