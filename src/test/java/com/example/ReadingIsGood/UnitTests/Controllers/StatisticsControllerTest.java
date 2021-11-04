package com.example.ReadingIsGood.UnitTests.Controllers;

import com.example.ReadingIsGood.Controllers.StatisticsController;
import com.example.ReadingIsGood.Services.StatisticsService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


@SpringBootTest
class StatisticsControllerTest {

/*    private StatisticsService statisticsService = mock(StatisticsService.class);
    private StatisticsController statisticsController = new StatisticsController(statisticsService);
    private Authentication principal;

    @Test
    void countOrders() {
        statisticsController.countOrders(principal);
        verify(statisticsService).countOrders(principal);
    }

    @Test
    void totalPurchaseAmount() {
        statisticsController.totalPurchaseAmount(principal);
        verify(statisticsService).totalPurchaseAmount(principal);
    }

    @Test
    void totalCountBooks() {
        statisticsController.totalCountBooks(principal);
        verify(statisticsService).totalCountBooks(principal);
    }

    @Test
    void monthlyStats() {
        statisticsController.monthlyStats(principal);
        verify(statisticsService).monthlyStats(principal);
    }*/
}