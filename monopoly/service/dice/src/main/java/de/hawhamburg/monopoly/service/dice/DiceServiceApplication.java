package de.hawhamburg.monopoly.service.dice;

import de.hawhamburg.monopoly.util.ServiceNames;
import de.hawhamburg.services.service.ServicesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class DiceServiceApplication {

    private static final Logger LOG = LoggerFactory.getLogger(DiceServiceApplication.class);

    @Autowired
    private ServicesService servicesService;

    // OF TODO container -> 5

    @PostConstruct
    public void registerToDiscoveryService() {
        de.hawhamburg.services.entity.Service service =
                new de.hawhamburg.services.entity.Service("This is a Dice service by LO", ServiceNames.NAME_OF_DICE_SERVICE,
                        "Dice Service Deluxe by LO", "https://vs-docker.informatik.haw-hamburg.de/ports/16315/dice");
        String result = servicesService.registerService(service);
        if (result == null) {
            LOG.warn("registration not successful!");
        } else {
            LOG.info("registration successful!");
        }
    }


    public static void main(String[] args) {
        SpringApplication.run(DiceServiceApplication.class,args);
    }
}