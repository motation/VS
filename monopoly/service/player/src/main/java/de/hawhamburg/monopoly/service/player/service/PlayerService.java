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


    public Player getPlayer(String playerId) {
        return playerRegistry.playerById(playerId);
    }

    public boolean createPlayer(Player player) {
        return playerRegistry.createPlayer(player);
    }
}
