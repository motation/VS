package de.hawhamburg.service.decks.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Ole on 04.11.2015.
 */
public interface DeckRMI extends Remote {
    Card chance(int gameId) throws RemoteException;
    Card community(int gameId) throws RemoteException;
}
