package de.hawhamburg.services.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/*
 * Created by Ole on 19.01.2016.
 */
@Configuration
public class LogConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(LogConfiguration.class);

    @Bean
    public RestTemplate restTemplateWithLogInterceptor() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }

}
