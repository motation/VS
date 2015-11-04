package de.hawhamburg.service.decks.service;

import de.hawhamburg.service.decks.rmi.Card;
import de.hawhamburg.service.decks.rmi.DeckRMI;
import de.hawhamburg.service.player.rmi.Deck;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Ole on 04.11.2015.
 */
@Service
public class DeckService extends UnicastRemoteObject implements DeckRMI {

    private static final long serialVersionUID = -7397538981299064961L;
    private List<Deck> decks = new ArrayList<>();
    public DeckService() throws RemoteException {
    }

    @Override
    public Card chance(int gameId) throws RemoteException {
        return decks.get(gameId).getAndRemoveRandomCard(true);
    }

    @Override
    public Card community(int gameId) throws RemoteException {
        return decks.get(gameId).getAndRemoveRandomCard(false);
    }


}
