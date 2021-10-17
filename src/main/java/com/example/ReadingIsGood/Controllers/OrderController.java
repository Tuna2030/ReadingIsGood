package com.example.ReadingIsGood.Controllers;

import com.example.ReadingIsGood.Authentication.AuthenticationResponse;
import com.example.ReadingIsGood.Models.BookModel;
import com.example.ReadingIsGood.Models.CustomerModel;
import com.example.ReadingIsGood.Models.OrderModel;
import com.example.ReadingIsGood.Repositories.BookRepository;
import com.example.ReadingIsGood.Repositories.CustomerRepository;
import com.example.ReadingIsGood.Repositories.OrderRepository;
import com.example.ReadingIsGood.Services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/orders")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final BookRepository bookRepository;

    @GetMapping("/all")
    public List<OrderModel> fetchAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/{orderId}")
    public OrderModel fetchOrder(@PathVariable String orderId){
        return orderService.getOrderById(orderId);
    }

    @GetMapping("/filterByDate")
    public List<OrderModel> filterOrderByDate(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                              @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate){
        List<OrderModel> orderModelList = orderService.getAllOrdersBetweenDates(startDate,endDate);
        return orderModelList;
    }

    @PostMapping("/create")
    public ResponseEntity<?>  createNewOrder(Authentication principal,@RequestBody HashMap<String, Integer> bookMap){
        OrderModel orderModel = new OrderModel();
        orderModel.setBookList(bookMap);
        orderModel.setOrderDate(new Date());

        CustomerModel customerModel = customerRepository.findCustomerModelByEmail(principal.getName());
        orderModel.setCustomerId(customerModel.getId());

        for(Map.Entry<String, Integer> entry : bookMap.entrySet()) {

            String key = entry.getKey();
            Integer value = entry.getValue();

            BookModel bookModel = bookRepository.findBookModelById(key);
            Integer newStock = Math.toIntExact(bookModel.getStock() - value);

            if(newStock < 0) return ResponseEntity.ok(new AuthenticationResponse("Insufficient stock! Book id: "+ bookModel.getId()));
        }

        for(Map.Entry<String, Integer> entry : bookMap.entrySet()) {

            String key = entry.getKey();
            Integer value = entry.getValue();

            BookModel bookModel = bookRepository.findBookModelById(key);
            Integer newStock = Math.toIntExact(bookModel.getStock() - value);

            bookModel.setStock(newStock);
            bookRepository.save(bookModel);
        }

        orderRepository.save(orderModel);
        return ResponseEntity.ok(new AuthenticationResponse("Successful order! Order number: " + orderModel.getId()));
    }

}
