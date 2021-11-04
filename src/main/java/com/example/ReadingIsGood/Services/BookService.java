package com.example.ReadingIsGood.Services;

import com.example.ReadingIsGood.Models.BookModel;
import com.example.ReadingIsGood.Repositories.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final LogServices logServices;

    public BookModel findBookModelById(String bookId) {
        return bookRepository.findBookModelById(bookId);
    }

    public void save(BookModel bookModel){
        bookRepository.save(bookModel);
    }

    public List<BookModel> getAllBooks(Authentication principal) {
        logServices.saveLog(principal, "All books listed.");
        return bookRepository.findAll();
    }

    public String addBook(Authentication principal, BookModel bookModel) {
        try {
            if (bookModel.getStock() < 1)
                return "Stock number should be a positive number.";
            else save(bookModel);
        }
        catch (Exception e) {
            return "Error adding " + bookModel.getName() + ", please check parameters.";
        }
        logServices.saveLog(principal, "New book added " + bookModel.getId() + "");
        return bookModel.getName() + " successfully added.";
    }

    public String updateBook(Authentication principal, String bookId, String stringStock){
        BookModel bookModel;
        int stock;
        try {
            bookModel = findBookModelById(bookId);
        }
        catch(Exception e) {
            return "Book id not found!";
        }

        if( bookModel == null)
            return "Book id not found!";

        try{
            stock = Integer.parseInt(stringStock);
        }
        catch(Exception e){
            return "Invalid book stock!";
        }

        if (stock < 1)
            return "Stock number should be a positive number.";
        try {
            bookModel.setStock(bookModel.getStock() + stock);
            save(bookModel);
        } catch (Exception e) {
            return "Error updating stock " + bookModel.getName();
        }
        logServices.saveLog(principal, "" + bookModel.getId() + " stock updated by +" + stock + "");
        return "Successful stock update " + bookModel.getName();
    }
}
