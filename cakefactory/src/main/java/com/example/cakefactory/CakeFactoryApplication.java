package com.example.cakefactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
class Initializer implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(Initializer.class);

    private CakesRepository cakesRepository;

    Initializer(CakesRepository cakesRepository) {
        this.cakesRepository = cakesRepository;
    }

    @Override
    public void run(String... args) {
        Cake cake = cakesRepository.save(new Cake("Red Velvet"));
        logger.info("Created new record with ID " + cake.id.toString());
    }
}

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, SecurityFilterAutoConfiguration.class})
@EnableDiscoveryClient
public class CakeFactoryApplication {

    @Bean
    @LoadBalanced
    RestTemplate warehouseRestTemplate(@Value("${warehouse.url:http://warehouse/}") String url) {
        return new RestTemplateBuilder().rootUri(url).build();
    }

    public static void main(String[] args) {
        SpringApplication.run(CakeFactoryApplication.class, args);
    }
}
