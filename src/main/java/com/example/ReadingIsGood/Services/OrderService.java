package com.example.ReadingIsGood.Services;

import com.example.ReadingIsGood.Models.OrderModel;
import com.example.ReadingIsGood.Repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public List<OrderModel> getAllOrders(){ return orderRepository.findAll(); }

    public List<OrderModel> getAllOrdersBetweenDates(LocalDate startDate, LocalDate endDate){  return orderRepository.findOrderModelByOrderDateBetween(startDate,endDate); }

    public List<OrderModel> getAllCustomerOrders(String customerId) { return orderRepository.findOrderModelByCustomerId(customerId); }

    public List<OrderModel> getAllCustomerOrdersPagable(String customerId, PageRequest pageRequest) { return orderRepository.findOrderModelByCustomerId(customerId, pageRequest); }

    public OrderModel getOrderById(String orderId) {
        OrderModel orderModel = orderRepository.findOrderModelById(orderId);
        if(orderModel == null) return null;
        return orderModel;
    }
}
