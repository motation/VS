package de.hawhamburg.monopoly.service.boards;

import de.hawhamburg.monopoly.util.ServiceNames;
import de.hawhamburg.services.service.ServicesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;

@ComponentScan({"de.hawhamburg.monopoly.service.boards", "de.hawhamburg.services"})
@SpringBootApplication
public class BoardsServiceApplication {

    private static final Logger LOG = LoggerFactory.getLogger(BoardsServiceApplication.class);

    @Autowired
    private ServicesService servicesService;

    @PostConstruct
    public void registerToDiscoveryService() {
        de.hawhamburg.services.entity.Service service =
                new de.hawhamburg.services.entity.Service("description", ServiceNames.NAME_OF_BOARDS_SERVICE,
                        "boards", "uri");

        String result = servicesService.registerService(service);
        if (result == null) {
            LOG.warn("registration not successful!");
        } else {
            LOG.info("registration successful!");
        }
    }


    public static void main(String[] args) {
        SpringApplication.run(BoardsServiceApplication.class,args);
    }
}