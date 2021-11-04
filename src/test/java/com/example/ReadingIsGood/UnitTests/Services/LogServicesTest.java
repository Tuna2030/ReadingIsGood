package com.example.ReadingIsGood.UnitTests.Services;

import com.example.ReadingIsGood.Models.CustomerModel;
import com.example.ReadingIsGood.Models.LogModel;
import com.example.ReadingIsGood.Repositories.CustomerRepository;
import com.example.ReadingIsGood.Repositories.LogRepository;
import com.example.ReadingIsGood.Services.LogServices;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Date;

import static org.mockito.Mockito.*;

class LogServicesTest {

/*    private LogRepository logRepository = mock(LogRepository.class);
    private CustomerRepository customerRepository = mock(CustomerRepository.class);
    private LogServices logServices = new LogServices(logRepository, customerRepository);
    private Authentication principal = new Authentication() {
        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return null;
        }

        @Override
        public Object getCredentials() {
            return null;
        }

        @Override
        public Object getDetails() {
            return null;
        }

        @Override
        public Object getPrincipal() {
            return null;
        }

        @Override
        public boolean isAuthenticated() {
            return false;
        }

        @Override
        public void setAuthenticated(boolean b) throws IllegalArgumentException {

        }

        @Override
        public String getName() {
            return "tuna@gmail.com";
        }
    };

    @Test
    void saveLog() {
        CustomerModel customerModel = new CustomerModel("Tuna","tuna@gmail.com", "1234");
        customerModel.setId("1234");
        when(customerRepository.findCustomerModelByEmail(principal.getName())).thenReturn(customerModel);
        LogModel logModel = new LogModel(customerRepository.findCustomerModelByEmail(customerModel.getEmail()).getId(), new Date(), "Test");
        logServices.saveLog(principal, "Test");
        verify(logRepository).save(logModel);
    }*/
}