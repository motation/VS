package de.hawhamburg.service.dice.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by unknown on 31.10.15.
 */
public interface DiceRMI extends Remote {
    Roll roll() throws RemoteException;
}
