package com.example.ReadingIsGood.Repositories;

import com.example.ReadingIsGood.Models.OrderModel;
import com.example.ReadingIsGood.Models.StatisticsModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends MongoRepository<OrderModel, String> {
    List<OrderModel> findOrderModelByCustomerId(String customerId);

    List<OrderModel> findOrderModelByCustomerId(String customerId, Pageable pageable);

    List<OrderModel> findOrderModelByOrderDateBetween(LocalDate startDate, LocalDate endDate);

    OrderModel findOrderModelById(String orderId);

    @Aggregation(pipeline = {
            "{$match: { 'customerId' : ?0 } }", "{ $group: { _id: { month: { $month: $orderDate } }, totalOrder: {$sum: 1}, month: {$first: { $month: $orderDate }}, totalBook: {$sum: '$totalBook' }, totalPurchaseAmount:{ $sum: {$toDecimal:'$orderAmount'} } } }", "{ '$sort' : { 'month' : -1 } }"})
    AggregationResults<StatisticsModel> groupMonthlyByCustomerId(String customerId);

}
