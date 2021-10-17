package com.example.ReadingIsGood.Controllers;

import com.example.ReadingIsGood.Authentication.AuthenticationResponse;
import com.example.ReadingIsGood.Models.BookModel;
import com.example.ReadingIsGood.Repositories.BookRepository;
import com.example.ReadingIsGood.Services.BookService;
import com.example.ReadingIsGood.Services.LogServices;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;
    private final BookRepository bookRepository;
    private final LogServices logServices;

    @GetMapping("/all")
    public List<BookModel> fetchAllBooks(Authentication principal) {
        logServices.saveLog(principal, "All books listed.");
        return bookService.getAllBooks();
    }

    @PostMapping("/add")
    public ResponseEntity<?> saveBook(Authentication principal, @RequestBody BookModel bookModel) {
        try {
            if (bookModel.getStock() < 1)
                return ResponseEntity.ok(new AuthenticationResponse("Stock number should be a positive number."));
            else bookRepository.save(bookModel);
        } catch (Exception e) {
            return ResponseEntity.ok(new AuthenticationResponse("Error adding " + bookModel.getName() + ", please check parameters."));
        }
        logServices.saveLog(principal, "New book added " + bookModel.getId() + "");
        return ResponseEntity.ok(new AuthenticationResponse(bookModel.getName() + " successfully added."));
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<?> updateBook(Authentication principal, @RequestBody int stock, @PathVariable String bookId) {

        BookModel bookModel = bookService.getBookById(bookId);
        if (stock < 1)
            return ResponseEntity.ok(new AuthenticationResponse("Stock number should be a positive number."));

        try {
            bookModel.setStock(bookModel.getStock() + stock);
            bookRepository.save(bookModel);
        } catch (Exception e) {
            return ResponseEntity.ok(new AuthenticationResponse("Error updating stock " + bookModel.getName()));
        }
        logServices.saveLog(principal, "" + bookModel.getId() + " stock updated +" + stock + "");
        return ResponseEntity.ok(new AuthenticationResponse("Successful stock update " + bookModel.getName()));
    }

}
