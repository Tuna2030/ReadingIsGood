package com.example.ReadingIsGood.Controllers;

import com.example.ReadingIsGood.Models.CustomerModel;
import com.example.ReadingIsGood.Services.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/all")
    public List<CustomerModel> fetchAllCustomers(){
        return customerService.getAllCustomers();
    }
}
