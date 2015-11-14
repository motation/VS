package de.hawhamburg.monopoly.service.dice.model;

/**
 * Created by Ole on 14.11.2015.
 */
public class Roll {
    private int diceValue;

    public Roll(int diceValue) {
        this.diceValue = diceValue;
    }

    public int getDiceValue() {
        return diceValue;
    }
}
