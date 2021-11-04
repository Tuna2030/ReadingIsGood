package com.example.ReadingIsGood.UnitTests.Controllers;

import com.example.ReadingIsGood.Controllers.OrderController;
import com.example.ReadingIsGood.Models.OrderModel;
import com.example.ReadingIsGood.Services.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;

import java.time.LocalDate;
import java.util.HashMap;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@SpringBootTest
class OrderControllerTest {

/*    private OrderService orderService = mock(OrderService.class);
    private OrderController orderController = new OrderController(orderService);
    private Authentication principal;

    @Test
    void getAllOrders() {
        orderController.getAllOrders(principal);
        verify(orderService).getAllOrders(principal);
    }

    @Test
    void getOrder() {
        orderController.getOrder(principal, "1234");
        verify(orderService).getOrderById(principal,"1234");

    }

    @Test
    void filterOrderByDate() {
        LocalDate startDate = LocalDate.of(2021, 10, 1);
        LocalDate endDate = LocalDate.of(2020, 11, 1);
        orderController.filterOrderByDate(principal, startDate, endDate);
        verify(orderService).getAllOrdersBetweenDates(principal, startDate, endDate);
    }

    @Test
    void createNewOrder() {
        HashMap<String, Integer> testMap = new HashMap<String, Integer>();
        orderController.createOrder(principal, testMap);
        verify(orderService).createOrder(principal, testMap);
    }*/
}