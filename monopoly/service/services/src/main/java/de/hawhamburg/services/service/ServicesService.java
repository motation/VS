package de.hawhamburg.services.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.hawhamburg.monopoly.util.RelaxedSSLValidation;
import de.hawhamburg.services.entity.Services;
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

    private static final String BASE_URI_HAW_DOCKER = "https://vs-docker.informatik.haw-hamburg.de/ports/8053";
    private static final String URI_TO_DISCOVERY_SERVICE = BASE_URI_HAW_DOCKER + "/services";
    private static final String URI_FOR_NAME_OF_SERVICE = URI_TO_DISCOVERY_SERVICE + "/of/name";
    private static final String USER_PASS_CRED = "user:pw";

    private static final Logger LOG = LoggerFactory.getLogger(ServicesService.class);

    public String registerService(de.hawhamburg.services.entity.Service service) {
        RelaxedSSLValidation.useRelaxedSSLValidation();
        RestTemplate restTemplate = new RestTemplate();
        String encodedBase64Credentials = Base64.getEncoder().encodeToString(USER_PASS_CRED.getBytes());
        String uri = URI_TO_DISCOVERY_SERVICE;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + encodedBase64Credentials);
        HttpEntity<de.hawhamburg.services.entity.Service> request = new HttpEntity<>(service, headers);
        ResponseEntity<String> response = null;
        try {
            LOG.info("trying to register the service: " + service.getName());
            response = restTemplate.exchange(uri, HttpMethod.POST, request, String.class);
        } catch (RestClientException e) {
            LOG.warn("Registration failed with: " + e.getMessage());
            return null;
        }
        return response.getBody();
    }

    private String getSuffixUrlForBaseDockerHAW(String nameOfService){
        RelaxedSSLValidation.useRelaxedSSLValidation();
        RestTemplate restTemplate = new RestTemplate();
        String encodedBase64Credentials = Base64.getEncoder().encodeToString(USER_PASS_CRED.getBytes());
        String uri = URI_FOR_NAME_OF_SERVICE + "/" +nameOfService;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + encodedBase64Credentials);
        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<Services> response = null;
        try {
            LOG.info("request to : " + uri);
            LOG.info("trying to fetch service id by name: " + nameOfService);
            response = restTemplate.exchange(uri, HttpMethod.GET, request, Services.class);
        } catch (RestClientException e) {
            LOG.warn("fetching service id by name failed with: " + e.getMessage());
            return null;
        }
        //OF TODO will be removed later
        return response.getBody().getServices().get(0);
    }

    public de.hawhamburg.services.entity.Service getServiceByName(String nameOfService){
        String suffixUrl = getSuffixUrlForBaseDockerHAW(nameOfService);
        RelaxedSSLValidation.useRelaxedSSLValidation();
        RestTemplate restTemplate = new RestTemplate();
        String encodedBase64Credentials = Base64.getEncoder().encodeToString(USER_PASS_CRED.getBytes());
        String uri = BASE_URI_HAW_DOCKER + suffixUrl;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + encodedBase64Credentials);
        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<String> response = null;
        try {
            LOG.info("request to : " + uri);
            LOG.info("trying to fetch service by name: " + nameOfService);
            response = restTemplate.exchange(uri, HttpMethod.GET, request, String.class);
        } catch (RestClientException e) {
            LOG.warn("fetching service by name failed with: " + e.getMessage());
            return null;
        }
        String json = response.getBody();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        de.hawhamburg.services.entity.Service service = gson.fromJson(json, de.hawhamburg.services.entity.Service.class);
        return service;
    }
}
