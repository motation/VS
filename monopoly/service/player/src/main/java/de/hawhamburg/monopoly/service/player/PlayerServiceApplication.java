package de.hawhamburg.monopoly.service.player;

import de.hawhamburg.monopoly.util.ServiceNames;
import de.hawhamburg.services.service.ServicesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;

@ComponentScan({"de.hawhamburg.monopoly.service.player", "de.hawhamburg.services"})
@SpringBootApplication
public class PlayerServiceApplication {

    private static final Logger LOG = LoggerFactory.getLogger(PlayerServiceApplication.class);

    @Autowired
    private ServicesService servicesService;

    // OF TODO container -> 3

    @PostConstruct
    public void registerToDiscoveryService() {
        de.hawhamburg.services.entity.Service service =
                new de.hawhamburg.services.entity.Service("This is a Player service by LO", ServiceNames.NAME_OF_PLAYER_SERVICE,
                        "Player Service Deluxe by LO", "https://vs-docker.informatik.haw-hamburg.de/ports/16313/player");
        String result = servicesService.registerService(service);
        if (result == null) {
            LOG.warn("registration not successful!");
        } else {
            LOG.info("registration successful!");
        }
    }


    public static void main(String[] args) {
        SpringApplication.run(PlayerServiceApplication.class,args);
    }
}