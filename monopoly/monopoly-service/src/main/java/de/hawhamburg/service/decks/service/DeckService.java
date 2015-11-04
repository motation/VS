package de.hawhamburg.service.decks.service;

import de.hawhamburg.service.decks.rmi.Card;
import de.hawhamburg.service.decks.rmi.DeckRMI;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;

/**
 * Created by Ole on 04.11.2015.
 */
@Service
public class DeckService implements DeckRMI {

    @Override
    public Card chance(int gameId) throws RemoteException {
        Card card = new Card();
        card.setName("Go to Jail");
        card.setText("Go to jail, do not travel across 'go' and don't receive $200");
        return card;
    }

    @Override
    public Card community(int gameId) throws RemoteException {
        Card card = new Card();
        card.setName("Go to Jail");
        card.setText("Go to jail, do not travel across 'go' and don't receive $200");
        return card;
    }
}