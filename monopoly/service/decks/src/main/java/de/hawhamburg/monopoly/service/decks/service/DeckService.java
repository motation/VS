package de.hawhamburg.monopoly.service.decks.service;

import de.hawhamburg.monopoly.service.decks.model.Card;
import de.hawhamburg.monopoly.service.decks.model.Deck;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ole on 04.11.2015.
 */
@Service
public class DeckService {

    private List<Deck> decks = new ArrayList<>();

    public DeckService() throws RemoteException {
    }


    public Card chance(int gameId) {
        return decks.get(gameId).getAndRemoveRandomCard(true);
    }


    public Card community(int gameId) {
        return decks.get(gameId).getAndRemoveRandomCard(false);
    }


}
