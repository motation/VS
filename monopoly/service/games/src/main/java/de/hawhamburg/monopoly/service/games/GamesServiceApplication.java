package de.hawhamburg.monopoly.service.games;

import de.hawhamburg.monopoly.util.ServiceNames;
import de.hawhamburg.services.service.ServicesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@ComponentScan({"de.hawhamburg.monopoly.service.games", "de.hawhamburg.services"})
@SpringBootApplication
public class GamesServiceApplication {

    private static final Logger LOG = LoggerFactory.getLogger(GamesServiceApplication.class);

    @Autowired
    private ServicesService servicesService;

    //OF TODO automatic build with port etc
    //OF TODO currently games service uses port 16310 -> container -> 0

    @PostConstruct
    public void registerToDiscoveryService() {
        de.hawhamburg.services.entity.Service service =
                new de.hawhamburg.services.entity.Service("This is a game service by LO", ServiceNames.NAME_OF_GAMES_SERVICE,
                        "Games Service Deluxe by LO", "https://vs-docker.informatik.haw-hamburg.de/ports/16310/games");

        String result = servicesService.registerService(service);
        if (result == null) {
            LOG.warn("registration not successful!");
        } else {
            LOG.info("registration successful!");
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(GamesServiceApplication.class, args);
    }
}