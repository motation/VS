package de.hawhamburg.monopoly.service.games.service;

import de.hawhamburg.monopoly.service.games.model.Estate;
import de.hawhamburg.monopoly.service.games.model.Game;
import de.hawhamburg.monopoly.util.Components;
import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by Ole on 24.11.2015.
 */
@Service
public class GameRegistry {
    private List<Game> games;

    private static final Logger LOG = LoggerFactory.getLogger(GameRegistry.class);

    @Autowired
    private RestTemplate restTemplate;

    @PostConstruct
    public void init() {
        games = new ArrayList<>();
    }

    public Game addGame(Components components) {

        String id = System.currentTimeMillis()+"";
        LOG.info("Trying to create game with id" + id);
        Game game = Game.builder()
                .withGameid(id)
                .withUri(components.getGame()+"/"+id)
                .withComponents(components)
                .build();
        if(!games.add(game)) {
            game = null;
            LOG.warn("Could not create game with id "+ id);
        }else{
            LOG.info("Created game with id" + id);
            createBrokerForGame(game);
        }

        return game;
    }

    public List<Game> getGames() {
        return this.games;
    }

    public Game findGameById(String gameId) {
        Game searchedGame = null;
        for (Game game : games) {
            if (game.getGameid().equals(gameId)){
                searchedGame = game;
                break;
            }
        }
        return searchedGame;
    }

    private void createBrokerForGame(Game game){
        String borkerUri = game.getComponents().getBroker();
        borkerUri += "/" + game.getGameid();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Game> entity = new HttpEntity<>(game,headers);
        ResponseEntity<String> response = restTemplate.exchange(borkerUri, HttpMethod.PUT,entity,String.class);

        if(response.getStatusCode().equals(HttpStatus.CREATED)){
            initFirstEstates(game,"0");
            initSecondEstates(game,"1");
            initThirdEstates(game,"2");
            initFourthEstates(game,"3");
            initFifthEstates(game,"4");
        }
    }

    private void putForRegisterProperty(Game game, Estate estate, String placeid){
        String borkerUri = game.getComponents().getBroker();
        borkerUri += "/" + game.getGameid() + "/places/" + placeid;
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Estate> entity = new HttpEntity<>(estate,headers);
        ResponseEntity<String> response =restTemplate.exchange(borkerUri, HttpMethod.PUT,entity,String.class);
    }

    private void initFirstEstates(Game game, String placeid){
        Estate estate = new Estate();
        estate.setHouses(0);
        estate.setValue(0);
        estate.setCost(Arrays.asList(0,0,0,0));
        estate.setRent(Arrays.asList(0,0,0,0));
        estate.setPlace("Los");
        putForRegisterProperty(game,estate,placeid);
    }

    private void initSecondEstates(Game game, String placeid){
        Estate estate = new Estate();
        estate.setHouses(0);
        estate.setValue(100);
        estate.setCost(Arrays.asList(20,30,40,50));
        estate.setRent(Arrays.asList(15,20,35,50));
        estate.setPlace("Kneipenstrasse");
        putForRegisterProperty(game,estate,placeid);
    }

    private void initThirdEstates(Game game, String placeid){
        Estate estate = new Estate();
        estate.setHouses(0);
        estate.setValue(100);
        estate.setCost(Arrays.asList(20,30,40,50));
        estate.setRent(Arrays.asList(5,10,15,25));
        estate.setPlace("Jail");
        putForRegisterProperty(game,estate,placeid);
    }

    private void initFourthEstates(Game game, String placeid){
        Estate estate = new Estate();
        estate.setHouses(0);
        estate.setValue(0);
        estate.setCost(Arrays.asList(0,0,0,0));
        estate.setRent(Arrays.asList(0,0,0,0));
        estate.setPlace("Gesellschaftskarte");
        putForRegisterProperty(game,estate,placeid);
    }

    private void initFifthEstates(Game game, String placeid){
        Estate estate = new Estate();
        estate.setHouses(0);
        estate.setValue(100);
        estate.setCost(Arrays.asList(20,30,40,50));
        estate.setRent(Arrays.asList(5,10,15,25));
        estate.setPlace("Burgstrasse");
        putForRegisterProperty(game,estate,placeid);
    }


}
