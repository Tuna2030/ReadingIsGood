package com.example.ReadingIsGood.Controllers;

import com.example.ReadingIsGood.Authentication.AuthenticationRequest;
import com.example.ReadingIsGood.Authentication.AuthenticationResponse;
import com.example.ReadingIsGood.Models.CustomerModel;
import com.example.ReadingIsGood.Repositories.CustomerRepository;
import com.example.ReadingIsGood.Services.CustomerService;
import com.example.ReadingIsGood.Utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private JwtUtils jwtUtils;

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

        UserDetails loadedUser = customerService.loadUserByUsername(email);
        String generatedToken = jwtUtils.generateToken(loadedUser);

        return ResponseEntity.ok(new AuthenticationResponse("Successful authentication generated token is " + generatedToken));

    }

}
