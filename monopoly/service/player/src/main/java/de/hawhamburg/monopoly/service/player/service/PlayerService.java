package de.hawhamburg.monopoly.service.player.service;

import de.hawhamburg.monopoly.service.player.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Ole on 04.11.2015.
 */
@Service
public class PlayerService {

    @Autowired
    private PlayerRegistry playerRegistry;

    private Player player;

    public Player getPlayer(String playerId) {
        return playerRegistry.playerById(playerId);
    }

    public boolean createPlayer(Player player) {
        return playerRegistry.createPlayer(player);
    }

    public Player getPlayer(){
        if (player == null){
            player = Player.builder()
                    .withId("123")
                    .withName("Peter")
                    .withPosition(0)
                    .withReady(true)
                    .withUri("")//TODO
                    .build();
        }
        return player;
    }

    public Player createPlayer(String id, String name, String uri){
        return Player.builder()
                .withId(id)
                .withName(name)
                .withPosition(0)
                .withReady(true)
                .withUri(uri)
                .build();
    }
}
