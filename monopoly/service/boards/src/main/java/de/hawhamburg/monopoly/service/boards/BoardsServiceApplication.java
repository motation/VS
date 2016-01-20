package de.hawhamburg.monopoly.service.boards;

import de.hawhamburg.monopoly.util.ServiceNames;
import de.hawhamburg.services.service.ServicesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.UnknownHostException;

@ComponentScan({"de.hawhamburg.monopoly.service.boards", "de.hawhamburg.services"})
@SpringBootApplication
public class BoardsServiceApplication {

    private static final Logger LOG = LoggerFactory.getLogger(BoardsServiceApplication.class);

    @Value("${server.port}")
    private int port;

    @Autowired
    private ServicesService servicesService;

    // OF TODO container -> 1

    @PostConstruct
    public void registerToDiscoveryService() {
        de.hawhamburg.services.entity.Service service =
                new de.hawhamburg.services.entity.Service("This is a boards service by LO", ServiceNames.NAME_OF_BOARDS_SERVICE,
                        "Boards Service Deluxe by LO", "https://vs-docker.informatik.haw-hamburg.de/ports/16311/boards");

        InetAddress ip;
        try {
            ip = InetAddress.getLocalHost();
            LOG.info("Current IP address : " + ip.getHostAddress());
            String urlOfService = ip.getHostAddress() + ":" + port;
            service.setUri("http://" + urlOfService + "/boards");
        } catch (UnknownHostException e) {
            LOG.info("error getting ip adress!");
            e.printStackTrace();
        }

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