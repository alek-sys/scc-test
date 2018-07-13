package com.example.demo;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/cake")
public class CakesController {

    private CakesRepository cakesRepository;

    public CakesController(CakesRepository cakesRepository) {
        this.cakesRepository = cakesRepository;
    }

    @GetMapping
    public Collection<Cake> getCakes() {
        return cakesRepository.findAll();
    }

    @GetMapping("/{id}")
    public HttpEntity<Cake> getCakeById(@PathVariable String id) {
        return cakesRepository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
