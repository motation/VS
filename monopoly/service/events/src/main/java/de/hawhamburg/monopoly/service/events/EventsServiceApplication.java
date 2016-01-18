package de.hawhamburg.monopoly.service.events;

import de.hawhamburg.monopoly.util.ServiceNames;
import de.hawhamburg.services.service.ServicesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;

/**
 * Created by Ole on 18.01.2016.
 */
@ComponentScan({"de.hawhamburg.monopoly.service.events", "de.hawhamburg.services"})
@SpringBootApplication
public class EventsServiceApplication {
    private static final Logger LOG = LoggerFactory.getLogger(EventsServiceApplication.class);

    @Autowired
    private ServicesService servicesService;

    //OF TODO automatic build with port etc
    //OF TODO currently games service uses port 16310 -> container -> 4

    @PostConstruct
    public void registerToDiscoveryService() {
        de.hawhamburg.services.entity.Service service =
                new de.hawhamburg.services.entity.Service("This is a events service by LO", ServiceNames.NAME_OF_EVENTS_SERVICE,
                        "Events Service Deluxe by LO", "https://vs-docker.informatik.haw-hamburg.de/ports/16314/events");

        String result = servicesService.registerService(service);
        if (result == null) {
            LOG.warn("registration not successful!");
        } else {
            LOG.info("registration successful!");
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(EventsServiceApplication.class, args);
    }
}
