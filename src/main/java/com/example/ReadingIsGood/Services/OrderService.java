package com.example.ReadingIsGood.Services;

import com.example.ReadingIsGood.Models.BookModel;
import com.example.ReadingIsGood.Models.CustomerModel;
import com.example.ReadingIsGood.Models.OrderModel;
import com.example.ReadingIsGood.Models.StatisticsModel;
import com.example.ReadingIsGood.Repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final BookService bookService;
    private final LogServices logServices;

    public List<OrderModel> getAllOrders(Authentication principal) {
        logServices.saveLog(principal, "All orders listed.");
        return orderRepository.findAll();
    }

    public List<OrderModel> getAllOrdersBetweenDates(Authentication principal, LocalDate startDate, LocalDate endDate) {
        logServices.saveLog(principal, "Orders between " + startDate + " and " + endDate + " are listed.");
        return orderRepository.findOrderModelByOrderDateBetween(startDate, endDate);
    }

    public List<OrderModel> getAllCustomerOrders(String customerId) {
        return orderRepository.findOrderModelByCustomerId(customerId);
    }

    public List<OrderModel> getAllCustomerOrdersPageable(Authentication principal, PageRequest pageRequest) {
        CustomerModel customerModel = customerService.findCustomerModelByEmail(principal);
        logServices.saveLog(principal, "Customer orders listed.");
        return orderRepository.findOrderModelByCustomerId(customerModel.getId(), pageRequest);
    }

    public void save(OrderModel orderModel) {
        orderRepository.save(orderModel);
    }

    public List<StatisticsModel> groupMonthlyByCustomerId(String customerId) {
        return orderRepository.groupMonthlyByCustomerId(customerId).getMappedResults();
    }

    public String createOrder(Authentication principal, HashMap<String, Integer> bookMap){
        try {
            if (bookMap.entrySet().toArray().length == 0) return "Order book id and buying quantity cannot be empty!";
            OrderModel orderModel = new OrderModel();
            try {
                orderModel.setBookList(bookMap);
            } catch (Exception e) {
                return "Error creating order.";
            }
            orderModel.setOrderDate(new Date());
            orderModel.setStatus("Order Placed");
            BigDecimal orderAmount = new BigDecimal(0);
            int bookCount = 0;

            CustomerModel customerModel = customerService.findCustomerModelByEmail(principal);

            orderModel.setCustomerId(customerModel.getId());

            for (Map.Entry<String, Integer> entry : bookMap.entrySet()) {

                String key = entry.getKey();
                Integer value = entry.getValue();

                if (value < 1)
                    return "Quantity should be a positive number.";

                BookModel bookModel = bookService.findBookModelById(key);
                int newStock = Math.toIntExact(bookModel.getStock() - value);

                if (newStock < 0)
                    return "Insufficient stock! Book id: " + bookModel.getId();
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

            save(orderModel);
            logServices.saveLog(principal, "Order " + orderModel.getId() + " created.");
            return "Successful order! Order number: " + orderModel.getId();
        } catch (Exception e) {
            return "Error creating order.";
        }
    }

    public OrderModel getOrderById(Authentication principal, String orderId) {
        OrderModel orderModel;
        try {
            orderModel = orderRepository.findOrderModelById(orderId);
            logServices.saveLog(principal, "Order " + orderId + " listed.");
        }
        catch(Exception e){
            orderModel = new OrderModel();
        }
        return orderModel;
    }
}
