package com.example.ReadingIsGood.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "customers")
public class CustomerModel {
    @Id
    private String id;
    private String name;
    @Indexed(unique = true)
    private String email;
    private String pass;

    public CustomerModel(String name, String email, String pass) {
        this.name = name;
        this.email = email;
        this.pass = pass;
    }
}
