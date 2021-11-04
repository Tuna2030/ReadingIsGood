package com.example.ReadingIsGood.Repositories;

import com.example.ReadingIsGood.Models.CustomerModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<CustomerModel, String> {
    CustomerModel findCustomerModelByEmail(String email);

}
