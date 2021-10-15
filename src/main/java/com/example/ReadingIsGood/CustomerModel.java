package com.example.ReadingIsGood;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class CustomerModel {
    @Id
    private String id;
    private String name;
    private String surname;
    private String email;
    private String pass;
}
