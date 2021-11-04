package com.example.ReadingIsGood.Services;

import com.example.ReadingIsGood.Models.CustomerModel;
import com.example.ReadingIsGood.Models.OrderModel;
import com.example.ReadingIsGood.Models.StatisticsModel;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class StatisticsService {

    private final OrderService orderService;
    private final CustomerService customerServices;
    private final LogServices logServices;

    public String countOrders(Authentication principal){
        CustomerModel customerModel = customerServices.findCustomerModelByEmail(principal);
        int orderCount = orderService.getAllCustomerOrders(customerModel.getId()).size();
        logServices.saveLog(principal, "Total order count listed.");
        return "Total order count is " + orderCount;
    }

    public String totalPurchaseAmount(Authentication principal){
        CustomerModel customerModel = customerServices.findCustomerModelByEmail(principal);
        List<OrderModel> orderModelList = orderService.getAllCustomerOrders(customerModel.getId());

        BigDecimal totalAmount = new BigDecimal(0);

        for (OrderModel om : orderModelList) {
            totalAmount = totalAmount.add(om.getOrderAmount());
        }

        logServices.saveLog(principal, "Total purchase amount listed.");
        return "Total purchase amount is " + totalAmount;
    }

    public String totalCountBooks(Authentication principal){
        CustomerModel customerModel = customerServices.findCustomerModelByEmail(principal);
        List<OrderModel> orderModelList = orderService.getAllCustomerOrders(customerModel.getId());

        Integer countBook = 0;

        for (OrderModel om : orderModelList) {
            for (Map.Entry<String, Integer> entry : om.getBookList().entrySet()) {
                Integer value = entry.getValue();
                countBook = countBook + value;
            }
        }
        logServices.saveLog(principal, "Total book purchase count listed.");
        return "Total book purchase count is " + countBook;
    }

    public String monthlyStats(Authentication principal) {
        CustomerModel customerModel = customerServices.findCustomerModelByEmail(principal);
        List<StatisticsModel> statisticsModelList = orderService.groupMonthlyByCustomerId(customerModel.getId());

        String table = "<!DOCTYPE html><html><style>table, th, td {border:1px solid black;text-align: center;}</style><body><table style=\"width:100%\"><tr><th>Month</th><th>Total Order Count</th><th>Total Book Count</th><th>Total Purchased Amount</th></tr>";
        for (StatisticsModel sm : statisticsModelList) {
            table = table + getTableRow(sm.getMonth(), sm.getTotalOrder(), sm.getTotalBook(), sm.getTotalPurchaseAmount());
        }
        logServices.saveLog(principal, "Monthly statistics table checked.");
        return table;
    }

    public String getTableRow(int month, int orderCount, int bookCount, BigDecimal totalPurchaseAmount) {
        String monthName = "";
        switch (month) {
            case 1:
                monthName = "January";
                break;
            case 2:
                monthName = "February";
                break;
            case 3:
                monthName = "March";
                break;
            case 4:
                monthName = "April";
                break;
            case 5:
                monthName = "May";
                break;
            case 6:
                monthName = "June";
                break;
            case 7:
                monthName = "July";
                break;
            case 8:
                monthName = "August";
                break;
            case 9:
                monthName = "September";
                break;
            case 10:
                monthName = "October";
                break;
            case 11:
                monthName = "November";
                break;
            case 12:
                monthName = "December";
                break;
        }
        return "<tr><td>" + monthName + "</td><td>" + orderCount + "</td><td>" + bookCount + "</td><td>" + totalPurchaseAmount + "</td></tr>";
    }

}
