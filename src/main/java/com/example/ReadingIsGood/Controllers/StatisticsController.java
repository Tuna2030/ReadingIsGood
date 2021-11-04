package com.example.ReadingIsGood.Controllers;

import com.example.ReadingIsGood.Authentication.AuthenticationResponse;
import com.example.ReadingIsGood.Services.StatisticsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/statistics")
@AllArgsConstructor
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping("/count")
    public ResponseEntity<?> countOrders(Authentication principal) {
        return ResponseEntity.ok(new AuthenticationResponse(statisticsService.countOrders(principal)));
    }

    @GetMapping("/totalPurchaseAmount")
    public ResponseEntity<?> totalPurchaseAmount(Authentication principal) {
        return ResponseEntity.ok(new AuthenticationResponse(statisticsService.totalPurchaseAmount(principal)));
    }

    @GetMapping("/totalCountBooks")
    public ResponseEntity<?> totalCountBooks(Authentication principal) {
        return ResponseEntity.ok(new AuthenticationResponse(statisticsService.totalCountBooks(principal)));
    }

    @GetMapping("/monthlyStats")
    public ResponseEntity<?> monthlyStats(Authentication principal) {
        return ResponseEntity.ok(statisticsService.monthlyStats(principal));
    }

}
