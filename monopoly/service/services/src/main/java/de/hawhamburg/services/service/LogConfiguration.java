package de.hawhamburg.services.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/*
 * Created by Ole on 19.01.2016.
 */
@Configuration
public class LogConfiguration {

    @Bean
    public RestTemplate restTemplateWithLogInterceptor() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }

}
