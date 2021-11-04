package com.example.ReadingIsGood.Controllers;

import com.example.ReadingIsGood.Authentication.AuthenticationResponse;
import com.example.ReadingIsGood.Models.BookModel;
import com.example.ReadingIsGood.Services.BookService;
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

    @GetMapping("/all")
    public List<BookModel> getAllBooks(Authentication principal) {
        return bookService.getAllBooks(principal);
    }

    @PostMapping("/add")
    public ResponseEntity<?> saveBook(Authentication principal, @RequestBody BookModel bookModel) {
        return ResponseEntity.ok(new AuthenticationResponse(bookService.addBook(principal, bookModel)));
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<?> updateBook(Authentication principal, @RequestBody String stock, @PathVariable String bookId) {
        return ResponseEntity.ok(new AuthenticationResponse(bookService.updateBook(principal,bookId,stock)));
    }

}
