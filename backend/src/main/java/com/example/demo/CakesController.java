package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collection;

class Cake {
    public Cake() {
    }

    public Cake(String name) {
        this.name = name;
    }

    public String name;
}

@RestController
@RequestMapping("/cake")
public class CakesController {

    @GetMapping
    public Collection<Cake> getCakes() {
        return Arrays.asList(new Cake("Curly Whirly"), new Cake("Passion Fizz"), new Cake("Red Velvet"));
    }

    @GetMapping("/{id}")
    public Cake getCakeById(@PathVariable Long id) {
        return new Cake("Red Velvet");
    }
}
