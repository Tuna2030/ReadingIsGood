package com.example.ReadingIsGood;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Data
@Document
public class OrderModel {
    @Id
    private String id;
    private List<BooksModel> bookModel;
    private CustomerModel customerModel;
    private Date orderDate;


}
