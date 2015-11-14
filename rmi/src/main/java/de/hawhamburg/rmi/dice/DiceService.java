package de.hawhamburg.rmi.dice;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

/**
 * Created by Ole on 14.11.2015.
 */
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
