package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WarehouseClient {

    private final RestTemplate restTemplate;

    WarehouseClient(@Value("${warehouse.url}") String url) {
        this.restTemplate = new RestTemplateBuilder().rootUri(url).build();
    }

    public Long getQtyInStock(String cakeId) {
        return restTemplate.getForObject("/stock/{cakeId}", Long.class, cakeId);
    }
}
