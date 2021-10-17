package com.example.ReadingIsGood.Repositories;

import com.example.ReadingIsGood.Models.OrderModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends MongoRepository<OrderModel, String>{
    List<OrderModel> findOrderModelByCustomerId(String customerId);

    List<OrderModel> findOrderModelByCustomerId(String customerId, Pageable pageable);

    List<OrderModel> findOrderModelByOrderDateBetween(LocalDate startDate, LocalDate endDate);
    OrderModel findOrderModelById(String orderId);
}
