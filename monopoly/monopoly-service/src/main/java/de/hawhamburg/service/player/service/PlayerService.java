package de.hawhamburg.service.player.service;

import de.hawhamburg.service.player.rmi.PlayerRMI;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Ole on 04.11.2015.
 */
@Service
public class PlayerService extends UnicastRemoteObject implements PlayerRMI {

    private static final long serialVersionUID = -7020942429428950227L;

    public PlayerService() throws RemoteException {
    }
}
