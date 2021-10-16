package com.example.ReadingIsGood;

import com.example.ReadingIsGood.Models.CustomerModel;
import com.example.ReadingIsGood.Repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@SpringBootApplication
public class ReadingIsGoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReadingIsGoodApplication.class, args);
	}


//	@Bean
//	CommandLineRunner runner(CustomerRepository repository, MongoTemplate mongoTemplate){
//		return args -> {
//			CustomerModel customerModel = new CustomerModel( "Tuna", "tuna@gmail.com","1234" );
//
//			// usingMongoTemplateAndQuery(repository, mongoTemplate, customerModel);
//			repository.findCustomerModelByEmail("tuna@gmail.com").ifPresentOrElse( c -> {
//				System.out.println("Customer already exists!");
//				} ,
//					() -> {
//				System.out.println("Inserting customer...");
//				repository.insert(customerModel);});
//		};
//	}

	private void usingMongoTemplateAndQuery(CustomerRepository repository, MongoTemplate mongoTemplate, CustomerModel customerModel) {
		Query query = new Query();
		query.addCriteria(Criteria.where("email").is("ece@gmail.com"));

		List<CustomerModel> customerModelList = mongoTemplate.find(query, CustomerModel.class);

		if(customerModelList.size() > 1){
			throw new IllegalStateException("found many customers with email ece@gmail.com");
		}

		if(customerModelList.isEmpty()){
			System.out.println("Inserting customer...");
			repository.insert(customerModel);
		}
		else System.out.println("Customer already exists!");
	}
}
