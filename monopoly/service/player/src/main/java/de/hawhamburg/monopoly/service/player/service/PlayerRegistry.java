package de.hawhamburg.monopoly.service.player.service;

import de.hawhamburg.monopoly.service.games.model.Place;
import de.hawhamburg.monopoly.service.player.model.Player;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ole on 24.11.2015.
 */
@Service
public class PlayerRegistry {
    private List<Player> players;

    @PostConstruct
    public void init() {
        players = new ArrayList<>();
        Place testPlace = Place.builder()
                .withName("Start")
                .build();
        Player testPlayer = Player.builder()
                .withId("123")
                .withName("Peter")
                .withPlace(testPlace)
                .withPosition(0)
                .withReady(true)
                .withUri("http://localhost:456/player/123")
                .build();
        players.add(testPlayer);
    }

    public Player playerById(String playerId) {
        Player searchedPlayer = null;
        for (Player player : players) {
            if (player.getId().equals(playerId)) {
                searchedPlayer = player;
                break;
            }
        }
        return searchedPlayer;
    }

    public boolean createPlayer(Player player) {
        return playerById(player.getId()) != null ? players.add(player) : false;
    }
}
