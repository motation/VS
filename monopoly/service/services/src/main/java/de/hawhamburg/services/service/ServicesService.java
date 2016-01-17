package de.hawhamburg.services.service;

import de.hawhamburg.monopoly.util.RelaxedSSLValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

/**
 * Created by Ole on 17.01.2016.
 */
@Service
public class ServicesService {

    private static final Logger LOG = LoggerFactory.getLogger(ServicesService.class);

    public String registerService(de.hawhamburg.services.entity.Service service) {
        RelaxedSSLValidation.useRelaxedSSLValidation();
        RestTemplate restTemplate = new RestTemplate();
        String encodedBase64Credentials = Base64.getEncoder().encodeToString("user:pw".getBytes());
        String uri = "https://vs-docker.informatik.haw-hamburg.de/ports/8053/services";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + encodedBase64Credentials);
        HttpEntity<de.hawhamburg.services.entity.Service> request = new HttpEntity<>(service, headers);
        ResponseEntity<String> response = null;
        try {
            LOG.info("trying to register the service: " + service.getName());
            response = restTemplate.exchange(uri, HttpMethod.POST, request, String.class);
        } catch (RestClientException e) {
            LOG.info("Registration failed with: " + e.getMessage());
            e.printStackTrace();
        }
        return response.getBody();
    }
}
