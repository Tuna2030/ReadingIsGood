package com.example.ReadingIsGood.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
public class StatisticsModel {
    private int totalOrder;
    private int totalBook;
    private int month;
    private BigDecimal totalPurchaseAmount;
}
