package de.hawhamburg.core.rmi.service;

import de.hawhamburg.core.rmi.entity.Roll;
import de.hawhamburg.core.rmi.interfaces.DiceRMI;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.util.Random;

/**
 * Created by unknown on 31.10.15.
 */
@Service
public class DiceService implements DiceRMI {

    @Override
    public Roll roll() throws RemoteException {
        return new Roll(createRandomNumber());
    }

    private int createRandomNumber(){
        Random random = new Random();
        return random.nextInt(6) + 1;
    }
}
