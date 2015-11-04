package de.hawhamburg.service;

import de.hawhamburg.service.decks.service.DeckService;
import de.hawhamburg.service.dice.service.DiceService;
import de.hawhamburg.service.player.service.PlayerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

@SpringBootApplication
public class MonopolyServiceApplication {

    public static final String DICE_NAME = "DiceRMIFJ";
    public static final String DECKS_NAME = "DecksRMIFJ";
    public static final String PLAYER_NAME = "PlayerRMIFJ";
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            Naming.rebind(DICE_NAME, new DiceService());
            Naming.rebind(DECKS_NAME, new DeckService());
            Naming.rebind(PLAYER_NAME, new PlayerService());
        }
        catch (MalformedURLException ex) {
            System.out.println("MalformerURLException "+ex.getMessage());
            ex.printStackTrace();
        }
        catch (RemoteException ex) {
            System.out.println("Remote Exception "+ex.getMessage());
            ex.printStackTrace();
        }
        SpringApplication.run(MonopolyServiceApplication.class,args);
    }
}