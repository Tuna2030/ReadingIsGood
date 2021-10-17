package com.example.ReadingIsGood.Controllers;

import com.example.ReadingIsGood.Models.CustomerModel;
import com.example.ReadingIsGood.Models.OrderModel;
import com.example.ReadingIsGood.Repositories.CustomerRepository;
import com.example.ReadingIsGood.Services.CustomerService;
import com.example.ReadingIsGood.Services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    private final CustomerRepository customerRepository;

    @GetMapping("/all")
    public List<CustomerModel> fetchAllCustomers(){
        return customerService.getAllCustomers();
    }

    @GetMapping("/orders")
    public List<OrderModel> fetchAllOrders(Authentication principal,
                                           @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                           @RequestParam(value = "size", required = false, defaultValue = "5") int size) {
        CustomerModel customerModel = customerRepository.findCustomerModelByEmail(principal.getName());
        List<OrderModel> orderModelList = orderService.getAllCustomerOrdersPagable(customerModel.getId(), PageRequest.of(page,size));
        return orderModelList;
    }
}
