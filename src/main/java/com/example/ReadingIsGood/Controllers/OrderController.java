package com.example.ReadingIsGood.Controllers;

import com.example.ReadingIsGood.Authentication.AuthenticationResponse;

import com.example.ReadingIsGood.Models.OrderModel;
import com.example.ReadingIsGood.Services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/all")
    public List<OrderModel> getAllOrders(Authentication principal) {
        return orderService.getAllOrders(principal);
    }

    @GetMapping("/{orderId}")
    public OrderModel getOrder(Authentication principal, @PathVariable String orderId) {
        return orderService.getOrderById(principal, orderId);
    }

    @GetMapping("/filterByDate")
    public List<OrderModel> filterOrderByDate(Authentication principal,
                                              @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                              @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return orderService.getAllOrdersBetweenDates(principal, startDate, endDate);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createOrder(Authentication principal, @RequestBody HashMap<String, Integer> bookMap) {
            return ResponseEntity.ok(new AuthenticationResponse(orderService.createOrder(principal, bookMap)));
    }

}
