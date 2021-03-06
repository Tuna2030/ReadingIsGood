package com.example.ReadingIsGood.Services;

import com.example.ReadingIsGood.Models.CustomerModel;
import com.example.ReadingIsGood.Repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService implements UserDetailsService {

    private final CustomerRepository customerRepository;
    private final LogServices logServices;

    public List<CustomerModel> getAllCustomers(Authentication principal) {
        logServices.saveLog(principal, "All customers listed.");
        return customerRepository.findAll();
    }

    public CustomerModel findCustomerModelByEmail(Authentication principal) {
        return customerRepository.findCustomerModelByEmail(principal.getName());
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        CustomerModel findByEmail = customerRepository.findCustomerModelByEmail(s);
        if (findByEmail == null) return null;

        String email = findByEmail.getEmail();
        String pass = findByEmail.getPass();

        return new User(email, pass, new ArrayList<>());
    }
}
