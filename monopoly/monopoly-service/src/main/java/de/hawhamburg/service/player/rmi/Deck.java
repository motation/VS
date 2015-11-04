package de.hawhamburg.service.player.rmi;

import de.hawhamburg.service.decks.rmi.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by abe180 on 04.11.2015.
 */
public class Deck {

    private List<Card> chanceCards;
    private List<Card> communityCards;


    public Deck(){
        initCards(true);
        initCards(false);
        //TODO add all Cards
    }

    public Card getAndRemoveRandomCard(boolean chance){
        Card card = randomCard(chance);
        removeCardFromGame(card, chance);
        return  card;
    }

    private Card randomCard(boolean chance){
        List<Card> cards = (chance)?chanceCards: communityCards;
        Random random = new Random();
        return cards.get(random.nextInt(cards.size()));

    }

    private void removeCardFromGame(Card card, boolean chance) {
        if(chance){
            chanceCards.remove(card);
            if (chanceCards.isEmpty())
                initCards(chance);
        }else{
            communityCards.remove(card);
            if (communityCards.isEmpty())
                initCards(chance);
        }
    }

    private void initCards(boolean chance){
        //TODO Decks füllen
        if(chance) {
            chanceCards = new ArrayList<>();
            Card card = new Card();
            card.setName("Go to Jail Chance");
            card.setText("Go to jail, do not travel across 'go' and don't receive $200");
            chanceCards.add(card);
        }else {
            communityCards = new ArrayList<>();
            Card card2 = new Card();
            card2.setName("Go to Jail Community");
            card2.setText("Go to jail, do not travel across 'go' and don't receive $200");
            chanceCards.add(card2);
        }
    }
}
