package com.example.ReadingIsGood.Controllers;

import com.example.ReadingIsGood.Authentication.AuthenticationResponse;
import com.example.ReadingIsGood.Models.BookModel;
import com.example.ReadingIsGood.Models.CustomerModel;
import com.example.ReadingIsGood.Models.OrderModel;
import com.example.ReadingIsGood.Services.BookService;
import com.example.ReadingIsGood.Services.CustomerService;
import com.example.ReadingIsGood.Services.LogServices;
import com.example.ReadingIsGood.Services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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
    private final CustomerService customerService;
    private final BookService bookService;
    private final LogServices logServices;

    @GetMapping("/all")
    public List<OrderModel> fetchAllOrders(Authentication principal) {
        logServices.saveLog(principal, "All orders listed.");
        return orderService.getAllOrders();
    }

    @GetMapping("/{orderId}")
    public OrderModel fetchOrder(Authentication principal, @PathVariable String orderId) {
        logServices.saveLog(principal, "Order " + orderId + " listed.");
        return orderService.getOrderById(orderId);
    }

    @GetMapping("/filterByDate")
    public List<OrderModel> filterOrderByDate(Authentication principal,
                                              @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                              @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<OrderModel> orderModelList = orderService.getAllOrdersBetweenDates(startDate, endDate);
        logServices.saveLog(principal, "Orders between " + startDate + " and " + endDate + " are listed.");
        return orderModelList;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createNewOrder(Authentication principal, @RequestBody HashMap<String, Integer> bookMap) {
        try {
            if (bookMap.get(0) == null) return ResponseEntity.ok(new AuthenticationResponse("Error creating order."));
            OrderModel orderModel = new OrderModel();
            try {
                orderModel.setBookList(bookMap);
            } catch (Exception e) {
                return ResponseEntity.ok(new AuthenticationResponse("Error creating order."));
            }
            orderModel.setOrderDate(new Date());
            orderModel.setStatus("Order Placed");
            BigDecimal orderAmount = new BigDecimal(0);
            Integer bookCount = 0;

            CustomerModel customerModel = customerService.findCustomerModelByEmail(principal);

            orderModel.setCustomerId(customerModel.getId());

            for (Map.Entry<String, Integer> entry : bookMap.entrySet()) {

                String key = entry.getKey();
                Integer value = entry.getValue();

                if (value < 1)
                    return ResponseEntity.ok(new AuthenticationResponse("Quantity should be a positive number."));

                BookModel bookModel = bookService.findBookModelById(key);
                Integer newStock = Math.toIntExact(bookModel.getStock() - value);

                if (newStock < 0)
                    return ResponseEntity.ok(new AuthenticationResponse("Insufficient stock! Book id: " + bookModel.getId()));
            }

            for (Map.Entry<String, Integer> entry : bookMap.entrySet()) {

                String key = entry.getKey();
                Integer value = entry.getValue();

                BookModel bookModel = bookService.findBookModelById(key);
                Integer newStock = Math.toIntExact(bookModel.getStock() - value);

                orderAmount = orderAmount.add(BigDecimal.valueOf(value).multiply(bookModel.getPrice()));
                bookCount = bookCount + value;

                bookModel.setStock(newStock);
                bookService.save(bookModel);
            }

            orderModel.setOrderAmount(orderAmount);
            orderModel.setTotalBook(bookCount);

            orderService.save(orderModel);
            logServices.saveLog(principal, "Order " + orderModel.getId() + " created.");
            return ResponseEntity.ok(new AuthenticationResponse("Successful order! Order number: " + orderModel.getId()));
        } catch (Exception e) {
            return ResponseEntity.ok(new AuthenticationResponse("Error creating order."));
        }
    }

}
