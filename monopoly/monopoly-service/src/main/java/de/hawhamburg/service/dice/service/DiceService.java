package de.hawhamburg.service.dice.service;

import de.hawhamburg.service.dice.rmi.DiceRMI;
import de.hawhamburg.service.dice.rmi.Roll;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

/**
 * Created by Ole on 04.11.2015.
 */
@Service
public class DiceService extends UnicastRemoteObject implements DiceRMI{


    private static final long serialVersionUID = -8925883553974534423L;

    public DiceService() throws RemoteException {
    }

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
