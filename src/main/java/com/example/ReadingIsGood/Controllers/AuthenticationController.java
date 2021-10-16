package com.example.ReadingIsGood.Controllers;

import com.example.ReadingIsGood.Authentication.AuthenticationRequest;
import com.example.ReadingIsGood.Authentication.AuthenticationResponse;
import com.example.ReadingIsGood.Models.CustomerModel;
import com.example.ReadingIsGood.Repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    private ResponseEntity<?> registerClient(@RequestBody AuthenticationRequest authenticationRequest){
        String email = authenticationRequest.getEmail();
        String password = authenticationRequest.getPass();
        String name = authenticationRequest.getName();

        CustomerModel customerModel = new CustomerModel(name,email,password);

        try{
            customerRepository.save(customerModel);
        }
        catch(Exception e){
            return ResponseEntity.ok(new AuthenticationResponse("Error during registration " + email));
        }

        return ResponseEntity.ok(new AuthenticationResponse("Successful registration " + email));
    }

    @PostMapping("/login")
    private ResponseEntity<?> authenticateClient(@RequestBody AuthenticationRequest authenticationRequest){
        String email = authenticationRequest.getEmail();
        String password = authenticationRequest.getPass();

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        }
        catch (Exception e){
            return ResponseEntity.ok(new AuthenticationResponse("Error during authentication " + email));
        }

        return ResponseEntity.ok(new AuthenticationResponse("Successful authentication " + email));

    }

}
