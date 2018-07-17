package com.example.cakefactory;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Cake {
    Cake() {

    }

    Cake(String name) {
        this.name = name;
    }

    Cake(String id, String name) {
        this.id = new ObjectId(id);
        this.name = name;
    }

    @Id
    ObjectId id;

    private String name;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
