package de.hawhamburg.monopoly.service.decks.controller;

import de.hawhamburg.monopoly.service.decks.model.Card;
import de.hawhamburg.monopoly.service.decks.service.DeckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by Ole on 04.11.2015.
 */
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
@RequestMapping(value="/decks")
@RestController
public class DeckController {

    @Autowired
    private DeckService deckService;

    @RequestMapping(value = "/{gameId}/chance", method = RequestMethod.GET)
    public Card chance(@PathVariable final int gameId) {
        return deckService.chance(gameId);
    }

    @RequestMapping(value = "/{gameId}/community", method = RequestMethod.GET)
    public Card community(@PathVariable final int gameId){
        return deckService.community(gameId);
    }
}
