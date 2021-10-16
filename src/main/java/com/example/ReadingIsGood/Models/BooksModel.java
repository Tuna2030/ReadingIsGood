package com.example.ReadingIsGood.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Document(collection = "books")
public class BooksModel {
    @Id
    private String id;
    private String name;
    private BigDecimal price;
    private Long stock;
}
