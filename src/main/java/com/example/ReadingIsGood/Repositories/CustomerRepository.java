package com.example.ReadingIsGood.Repositories;

import com.example.ReadingIsGood.Models.CustomerModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CustomerRepository extends MongoRepository <CustomerModel, String> {
    CustomerModel findCustomerModelByEmail(String email);
    //Optional<CustomerModel> findCustomerModelByEmail(String email);
}
