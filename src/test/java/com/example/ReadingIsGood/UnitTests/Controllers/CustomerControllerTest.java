package com.example.ReadingIsGood.UnitTests.Controllers;

import com.example.ReadingIsGood.Controllers.CustomerController;
import com.example.ReadingIsGood.Services.BookService;
import com.example.ReadingIsGood.Services.CustomerService;
import com.example.ReadingIsGood.Services.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@SpringBootTest
class CustomerControllerTest {

 /*   private CustomerService customerService = mock(CustomerService.class);
    private OrderService orderService = mock(OrderService.class);
    private CustomerController customerController = new CustomerController(customerService, orderService);
    private Authentication principal;

    @Test
    void getAllCustomers() {
        customerController.getAllCustomers(principal);
        verify(customerService).getAllCustomers(principal);
    }

    @Test
    void getAllOrders() {
        customerController.getAllOrders(principal,5, 2);
        verify(orderService).getAllCustomerOrdersPageable(principal, PageRequest.of(5, 2));
    }*/
}