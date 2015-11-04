package de.hawhamburg.service.dice.service;

import de.hawhamburg.service.dice.rmi.DiceRMI;
import de.hawhamburg.service.dice.rmi.Roll;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.util.Random;

/**
 * Created by Ole on 04.11.2015.
 */
@Service
public class DiceService implements DiceRMI {
    @Override
    public Roll roll() throws RemoteException {
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
