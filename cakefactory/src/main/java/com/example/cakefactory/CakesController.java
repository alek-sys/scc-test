package com.example.cakefactory;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@CrossOrigin
@RestController
@RequestMapping("/cake")
public class CakesController {

    private CakesRepository cakesRepository;
    private WarehouseClient warehouseClient;

    public CakesController(CakesRepository cakesRepository, WarehouseClient warehouseClient) {
        this.cakesRepository = cakesRepository;
        this.warehouseClient = warehouseClient;
    }

    @GetMapping
    public Collection<Cake> getCakes() {
        return cakesRepository.findAll();
    }

    @GetMapping("/{id}")
    public HttpEntity<CakeResponseModel> getCakeById(@PathVariable String id) {
        return cakesRepository
                .findById(id)
                .map(this::getResponseModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    private CakeResponseModel getResponseModel(Cake cake) {
        Long inStock = this.warehouseClient.getQtyInStock(cake.getId().toString());
        return new CakeResponseModel(cake.getName(), inStock);
    }
}
