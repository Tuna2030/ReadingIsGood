package com.example.ReadingIsGood.Services;

import com.example.ReadingIsGood.Models.BookModel;
import com.example.ReadingIsGood.Repositories.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<BookModel> getAllBooks() {
        return bookRepository.findAll();
    }

    public BookModel getBookById(String bookId) {
        BookModel bookModel = bookRepository.findBookModelById(bookId);
        if(bookModel == null) return null;
        return bookModel;
    }
}
