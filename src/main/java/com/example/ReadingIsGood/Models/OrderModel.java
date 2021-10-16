package com.example.ReadingIsGood.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Data
@Document(collection = "orders")
public class OrderModel {
    @Id
    private String id;
    private List<BookModel> bookModel;
    private CustomerModel customerModel;
    private Date orderDate;


}
