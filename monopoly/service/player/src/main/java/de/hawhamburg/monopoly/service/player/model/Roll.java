package de.hawhamburg.monopoly.service.player.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @JsonIgnore
    public boolean isValid() {
        return number >1 && number < 6;
    }
}
