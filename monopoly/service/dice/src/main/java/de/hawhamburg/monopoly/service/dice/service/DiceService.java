package de.hawhamburg.monopoly.service.dice.service;

import de.hawhamburg.monopoly.service.dice.model.Roll;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.util.Random;

/**
 * Created by Ole on 04.11.2015.
 */
@Service
public class DiceService{

    public Roll roll() {
        return new Roll(createRandomNumber());
    }

    /*
     creates a random number from 1 to 6
     */
    private int createRandomNumber(){
        Random random = new Random();
        return random.nextInt(6) + 1;
    }
}
