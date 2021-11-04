package com.example.ReadingIsGood.UnitTests.Services;

import com.example.ReadingIsGood.Models.BookModel;
import com.example.ReadingIsGood.Models.CustomerModel;
import com.example.ReadingIsGood.Models.OrderModel;
import com.example.ReadingIsGood.Models.StatisticsModel;
import com.example.ReadingIsGood.Repositories.OrderRepository;
import com.example.ReadingIsGood.Services.BookService;
import com.example.ReadingIsGood.Services.CustomerService;
import com.example.ReadingIsGood.Services.LogServices;
import com.example.ReadingIsGood.Services.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {

/*    private OrderRepository orderRepository = mock(OrderRepository.class);
    private CustomerService customerService = mock(CustomerService.class);
    private BookService bookService = mock(BookService.class);
    private LogServices logServices = mock(LogServices.class);
    private OrderService orderService = new OrderService(orderRepository, customerService, bookService, logServices);
    Authentication principal = new Authentication() {
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
    void getAllOrders() {
        orderService.getAllOrders(principal);
        verify(orderRepository).findAll();
    }

    @Test
    void getAllOrdersBetweenDates() {
        LocalDate startDate = LocalDate.of(2021, 10, 1);
        LocalDate endDate = LocalDate.of(2020, 11, 1);
        orderService.getAllOrdersBetweenDates(principal, startDate, endDate);
        verify(orderRepository).findOrderModelByOrderDateBetween(startDate, endDate);
    }

    @Test
    void getAllCustomerOrders() {
        orderService.getAllCustomerOrders("tuna@gmail.com");
        verify(orderRepository).findOrderModelByCustomerId("tuna@gmail.com");
    }

    @Test
    void getAllCustomerOrdersPageable() {
        CustomerModel customerModel = new CustomerModel("Tuna", "tuna@gmail.com", "1234");
        customerModel.setId("1234");
        when(customerService.findCustomerModelByEmail(principal)).thenReturn(customerModel);
        orderService.getAllCustomerOrdersPageable(principal, PageRequest.of(5, 2));
        verify(orderRepository).findOrderModelByCustomerId(customerModel.getId(), PageRequest.of(5, 2));
    }

    @Test
    void save() {
        OrderModel orderModel = new OrderModel();
        orderService.save(orderModel);
        verify(orderRepository).save(orderModel);
    }

    @Test
    void createOrder() {
        HashMap<String, Integer> testMap = new HashMap<>();
        testMap.put("1234", 1);
        CustomerModel customerModel = new CustomerModel("Tuna", "tuna@gmail.com", "1234");
        customerModel.setId("1234");
        BookModel bookModel = new BookModel("Hello World!", new BigDecimal(5), 10);
        bookModel.setId("1234");
        when(customerService.findCustomerModelByEmail(principal)).thenReturn(customerModel);
        when(bookService.findBookModelById("1234")).thenReturn(bookModel);

        OrderModel orderModel = new OrderModel();
        orderModel.setId("1234");
        orderModel.setBookList(testMap);
        orderModel.setOrderDate(new Date());
        orderModel.setStatus("Order Placed");
        orderModel.setCustomerId(customerService.findCustomerModelByEmail(principal).getId());

        BigDecimal orderAmount = new BigDecimal(0);
        int bookCount = 0;

        for (Map.Entry<String, Integer> entry : testMap.entrySet()) {

            Integer value = entry.getValue();

            Integer newStock = Math.toIntExact(bookService.findBookModelById("1234").getStock() - value);

            orderAmount = orderAmount.add(BigDecimal.valueOf(value).multiply(bookModel.getPrice()));
            bookCount = bookCount + value;

            bookModel.setStock(newStock);
        }

        orderModel.setOrderAmount(orderAmount);
        orderModel.setTotalBook(bookCount);

        assertEquals("Successful order! Order number: null", orderService.createOrder(principal, testMap));
    }

    @Test
    void getOrderById() {
        orderService.getOrderById(principal, "1234");
        verify(orderRepository).findOrderModelById("1234");
    }*/
}