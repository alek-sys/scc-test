package com.example.demo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CakesRepository extends MongoRepository<Cake, String> {
}
