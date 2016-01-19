package de.hawhamburg.monopoly.service.dice.model;

/**
 * Created by Ole on 14.11.2015.
 */
public class Roll {
    private int number;

    public Roll(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public boolean isValid() {
        return number >1 && number < 6;
    }
}
