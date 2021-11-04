package com.example.ReadingIsGood.Repositories;

import com.example.ReadingIsGood.Models.BookModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<BookModel, String> {
    BookModel findBookModelById(String bookId);
    BookModel findBookModelByName(String bookName);
}
