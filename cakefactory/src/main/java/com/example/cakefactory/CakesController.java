package com.example.cakefactory;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    /**
     * Return list of cakes in the bakery. Note that not all the listed cakes are in stock, check qty property.
     * @return a list of available cakes
     */
    @GetMapping
    public List<CakeResponseModel> getCakes() {
        return cakesRepository
                .findAll()
                .stream()
                .map(this::getResponseModel)
                .collect(Collectors.toList());
    }

    /**
     * @param id ID of the cake.
     */
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
