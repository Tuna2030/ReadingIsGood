package com.example.ReadingIsGood.Repositories;

import com.example.ReadingIsGood.Models.LogModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogRepository extends MongoRepository<LogModel, String> {
}
