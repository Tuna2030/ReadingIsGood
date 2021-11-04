package com.example.ReadingIsGood.UnitTests.Services;

import com.example.ReadingIsGood.Models.CustomerModel;
import com.example.ReadingIsGood.Repositories.CustomerRepository;
import com.example.ReadingIsGood.Services.CustomerService;
import com.example.ReadingIsGood.Services.LogServices;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

/*    private CustomerRepository customerRepository = mock(CustomerRepository.class);
    private LogServices logServices = mock(LogServices.class);
    private CustomerService customerService = new CustomerService(customerRepository, logServices);
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
    void getAllCustomers() {
        customerService.getAllCustomers(principal);
        verify(customerRepository).findAll();
        List<CustomerModel> customerModelList = new ArrayList<CustomerModel>();
        when(customerRepository.findAll()).thenReturn(customerModelList);
        assertEquals(customerModelList, customerService.getAllCustomers(principal));
    }

    @Test
    void findCustomerModelByEmail() {
        CustomerModel customerModel = new CustomerModel("Tuna", "tuna@gmail.com","1234");
        customerService.findCustomerModelByEmail(principal);
        verify(customerRepository).findCustomerModelByEmail(principal.getName());
        when(customerRepository.findCustomerModelByEmail(principal.getName())).thenReturn(customerModel);
        assertEquals(customerModel, customerService.findCustomerModelByEmail(principal));
    }

    @Test
    void loadUserByUsername() {
        CustomerModel customerModel = new CustomerModel("Tuna", "tuna@gmail.com","1234");
        UserDetails userDetails = new User("tuna@gmail.com","1234", new ArrayList<>());
        customerService.loadUserByUsername("tuna@gmail.com");
        verify(customerRepository).findCustomerModelByEmail("tuna@gmail.com");
        when(customerRepository.findCustomerModelByEmail("tuna@gmail.com")).thenReturn(customerModel);
        assertEquals(userDetails, customerService.loadUserByUsername("tuna@gmail.com"));

    }*/
}