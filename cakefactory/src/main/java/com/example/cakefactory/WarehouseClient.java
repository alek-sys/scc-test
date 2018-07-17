package com.example.cakefactory;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WarehouseClient {

    private final RestTemplate warehouseRestTemplate;

    WarehouseClient(RestTemplate warehouseRestTemplate) {
        this.warehouseRestTemplate = warehouseRestTemplate;
    }

    Long getQtyInStock(String cakeId) {
        return warehouseRestTemplate.getForObject("/stock/{cakeId}", Long.class, cakeId);
    }
}
