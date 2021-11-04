package com.example.ReadingIsGood.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

@Data
@Document(collection = "orders")
public class OrderModel {
    @Id
    private String id;
    private HashMap<String, Integer> bookList;
    private String customerId;
    private Integer totalBook;
    private Date orderDate;
    private BigDecimal orderAmount;
    private String status;

}
