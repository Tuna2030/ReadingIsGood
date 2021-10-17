package com.example.ReadingIsGood.Controllers;

import com.example.ReadingIsGood.Authentication.AuthenticationResponse;
import com.example.ReadingIsGood.Models.CustomerModel;
import com.example.ReadingIsGood.Models.OrderModel;
import com.example.ReadingIsGood.Models.StatisticsModel;
import com.example.ReadingIsGood.Services.CustomerService;
import com.example.ReadingIsGood.Services.LogServices;
import com.example.ReadingIsGood.Services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/statistics")
@AllArgsConstructor
public class StatisticsController {

    private final OrderService orderService;
    private final CustomerService customerServices;
    private final LogServices logServices;

    @GetMapping("/count")
    public ResponseEntity<?> countOrders(Authentication principal) {
        CustomerModel customerModel = customerServices.findCustomerModelByEmail(principal);
        int orderCount = orderService.getAllCustomerOrders(customerModel.getId()).size();
        logServices.saveLog(principal, "Total order count listed.");
        return ResponseEntity.ok(new AuthenticationResponse("Total order count is " + orderCount));
    }

    @GetMapping("/totalPurchaseAmount")
    public ResponseEntity<?> totalPurchaseAmount(Authentication principal) {
        CustomerModel customerModel = customerServices.findCustomerModelByEmail(principal);
        List<OrderModel> orderModelList = orderService.getAllCustomerOrders(customerModel.getId());

        BigDecimal totalAmount = new BigDecimal(0);

        for (OrderModel om : orderModelList) {
            totalAmount = totalAmount.add(om.getOrderAmount());
        }

        logServices.saveLog(principal, "Total purchase amount listed.");
        return ResponseEntity.ok(new AuthenticationResponse("Total purchase amount is " + totalAmount));
    }

    @GetMapping("/totalCountBooks")
    public ResponseEntity<?> totalCountBooks(Authentication principal) {
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
        return ResponseEntity.ok(new AuthenticationResponse("Total book purchase count is " + countBook));
    }

    @GetMapping("/monthlyStats")
    public ResponseEntity<?> monthlyStats(Authentication principal) {
        CustomerModel customerModel = customerServices.findCustomerModelByEmail(principal);
        List<StatisticsModel> statisticsModelList = orderService.groupMonthlyByCustomerId(customerModel.getId());

        String table = "<!DOCTYPE html><html><style>table, th, td {border:1px solid black;text-align: center;}</style><body><table style=\"width:100%\"><tr><th>Month</th><th>Total Order Count</th><th>Total Book Count</th><th>Total Purchased Amount</th></tr>";
        for (StatisticsModel sm : statisticsModelList) {
            table = table + getTableRow(sm.getMonth(), sm.getTotalOrder(), sm.getTotalBook(), sm.getTotalPurchaseAmount());
        }
        logServices.saveLog(principal, "Monthly statistics table checked.");
        return ResponseEntity.ok(table);
    }

    private String getTableRow(int month, int orderCount, int bookCount, BigDecimal totalPurchaseAmount) {
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
