package com.example.ReadingIsGood.Services;

import com.example.ReadingIsGood.Models.LogModel;
import com.example.ReadingIsGood.Repositories.CustomerRepository;
import com.example.ReadingIsGood.Repositories.LogRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class LogServices {

    private final LogRepository logRepository;

    private final CustomerRepository customerRepository;

    public void saveLog(Authentication principal, String logText) {
        logRepository.save(new LogModel(customerRepository.findCustomerModelByEmail(principal.getName()).getId(), new Date(), logText));
    }

}
