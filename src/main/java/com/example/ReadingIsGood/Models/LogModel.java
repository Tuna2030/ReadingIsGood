package com.example.ReadingIsGood.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "log")
public class LogModel {
    @Id
    private String id;
    private String customerId;
    private Date logDate;
    private String logString;

    public LogModel(String customerId, Date logDate, String logString) {
        this.customerId = customerId;
        this.logDate = logDate;
        this.logString = logString;
    }
}
