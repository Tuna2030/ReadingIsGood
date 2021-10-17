package com.example.ReadingIsGood.Controllers;

import com.example.ReadingIsGood.Authentication.AuthenticationResponse;
import com.example.ReadingIsGood.Models.BookModel;
import com.example.ReadingIsGood.Models.CustomerModel;
import com.example.ReadingIsGood.Models.OrderModel;
import com.example.ReadingIsGood.Repositories.BookRepository;
import com.example.ReadingIsGood.Repositories.CustomerRepository;
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
    private final CustomerRepository customerRepository;
    private final BookRepository bookRepository;

    @GetMapping("/count")
    public ResponseEntity<?> countOrders(Authentication principal){
        CustomerModel customerModel = customerRepository.findCustomerModelByEmail(principal.getName());
        int orderCount = orderService.getAllCustomerOrders(customerModel.getId()).size();
        return ResponseEntity.ok(new AuthenticationResponse("Total order count is "+ orderCount));
    }

    @GetMapping("/totalPurchaseAmount")
    public ResponseEntity<?> totalPurchaseAmount(Authentication principal){
        CustomerModel customerModel = customerRepository.findCustomerModelByEmail(principal.getName());
        List<OrderModel> orderModelList = orderService.getAllCustomerOrders(customerModel.getId());

        BigDecimal totalAmount = new BigDecimal(0);

        for( OrderModel om : orderModelList){
            for(Map.Entry<String, Integer> entry : om.getBookList().entrySet()) {

                String key = entry.getKey();
                Integer value = entry.getValue();

                BookModel bookModel = bookRepository.findBookModelById(key);
                totalAmount = totalAmount.add(bookModel.getPrice().multiply(BigDecimal.valueOf(value)));
            }
        }

        return ResponseEntity.ok(new AuthenticationResponse("Total purchase amount is "+ totalAmount));
    }

    @GetMapping("/totalCountBooks")
    public ResponseEntity<?> totalCountBooks(Authentication principal){
        CustomerModel customerModel = customerRepository.findCustomerModelByEmail(principal.getName());
        List<OrderModel> orderModelList = orderService.getAllCustomerOrders(customerModel.getId());

        Integer countBook = 0;

        for( OrderModel om : orderModelList){
            for(Map.Entry<String, Integer> entry : om.getBookList().entrySet()) {
                Integer value = entry.getValue();
                countBook = countBook + value;
            }
        }

        return ResponseEntity.ok(new AuthenticationResponse("Total purchase amount is "+ countBook));
    }

}
