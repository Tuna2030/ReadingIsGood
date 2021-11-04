package com.example.ReadingIsGood.UnitTests.Services;

import com.example.ReadingIsGood.Models.CustomerModel;
import com.example.ReadingIsGood.Models.OrderModel;
import com.example.ReadingIsGood.Models.StatisticsModel;
import com.example.ReadingIsGood.Services.CustomerService;
import com.example.ReadingIsGood.Services.LogServices;
import com.example.ReadingIsGood.Services.OrderService;
import com.example.ReadingIsGood.Services.StatisticsService;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StatisticsServiceTest {

/*    private OrderService orderService = mock(OrderService.class);
    private CustomerService customerService = mock(CustomerService.class);
    private LogServices logServices = mock(LogServices.class);
    private StatisticsService statisticsService = new StatisticsService(orderService, customerService, logServices);
    private Authentication principal = new Authentication() {
        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return null;
        }

        @Override
        public Object getCredentials() {
            return null;
        }

        @Override
        public Object getDetails() {
            return null;
        }

        @Override
        public Object getPrincipal() {
            return null;
        }

        @Override
        public boolean isAuthenticated() {
            return false;
        }

        @Override
        public void setAuthenticated(boolean b) throws IllegalArgumentException {

        }

        @Override
        public String getName() {
            return "tuna@gmail.com";
        }
    };

    @Test
    void countOrders() {
        CustomerModel customerModel = new CustomerModel("Tuna", "tuna@gmail.com", "1234");
        customerModel.setId("1234");
        List<OrderModel> orderModelList = new ArrayList<OrderModel>();
        when(customerService.findCustomerModelByEmail(principal)).thenReturn(customerModel);
        when(orderService.getAllCustomerOrders(customerModel.getId())).thenReturn(orderModelList);
        assertEquals("Total order count is " + orderModelList.size(), statisticsService.countOrders(principal));
    }

    @Test
    void totalPurchaseAmount() {
        CustomerModel customerModel = new CustomerModel("Tuna", "tuna@gmail.com", "1234");
        customerModel.setId("1234");
        List<OrderModel> orderModelList = new ArrayList<OrderModel>();
        when(customerService.findCustomerModelByEmail(principal)).thenReturn(customerModel);
        when(orderService.getAllCustomerOrders(customerModel.getId())).thenReturn(orderModelList);
        BigDecimal totalAmount = new BigDecimal(0);

        for (OrderModel om : orderModelList) {
            totalAmount = totalAmount.add(om.getOrderAmount());
        }
        assertEquals("Total purchase amount is " + totalAmount, statisticsService.totalPurchaseAmount(principal));
    }

    @Test
    void totalCountBooks() {
        CustomerModel customerModel = new CustomerModel("Tuna", "tuna@gmail.com", "1234");
        customerModel.setId("1234");
        List<OrderModel> orderModelList = new ArrayList<OrderModel>();
        when(customerService.findCustomerModelByEmail(principal)).thenReturn(customerModel);
        when(orderService.getAllCustomerOrders(customerModel.getId())).thenReturn(orderModelList);

        Integer countBook = 0;

        for (OrderModel om : orderModelList) {
            for (Map.Entry<String, Integer> entry : om.getBookList().entrySet()) {
                Integer value = entry.getValue();
                countBook = countBook + value;
            }
        }

        assertEquals("Total book purchase count is " + countBook, statisticsService.totalCountBooks(principal));
    }

    @Test
    void monthlyStats() {
        CustomerModel customerModel = new CustomerModel("Tuna", "tuna@gmail.com", "1234");
        customerModel.setId("1234");
        List<StatisticsModel> statisticsModelList = new ArrayList<StatisticsModel>();
        when(customerService.findCustomerModelByEmail(principal)).thenReturn(customerModel);
        when(orderService.groupMonthlyByCustomerId(customerModel.getId())).thenReturn(statisticsModelList);

        String table = "<!DOCTYPE html><html><style>table, th, td {border:1px solid black;text-align: center;}</style><body><table style=\"width:100%\"><tr><th>Month</th><th>Total Order Count</th><th>Total Book Count</th><th>Total Purchased Amount</th></tr>";
        for (StatisticsModel sm : statisticsModelList) {
            table = table + statisticsService.getTableRow(sm.getMonth(), sm.getTotalOrder(), sm.getTotalBook(), sm.getTotalPurchaseAmount());
        }

        assertEquals(table, statisticsService.monthlyStats(principal));

    }*/
}