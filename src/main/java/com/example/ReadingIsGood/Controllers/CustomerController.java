package com.example.ReadingIsGood.Controllers;

import com.example.ReadingIsGood.Models.CustomerModel;
import com.example.ReadingIsGood.Models.OrderModel;
import com.example.ReadingIsGood.Services.CustomerService;
import com.example.ReadingIsGood.Services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private final OrderService orderService;


    @GetMapping("/all")
    public List<CustomerModel> getAllCustomers(Authentication principal) {
        return customerService.getAllCustomers(principal);
    }

    @GetMapping("/orders")
    public List<OrderModel> getAllOrders(Authentication principal,
                                           @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                           @RequestParam(value = "size", required = false, defaultValue = "5") int size) {
        return orderService.getAllCustomerOrdersPageable(principal, PageRequest.of(page, size));
    }
}
