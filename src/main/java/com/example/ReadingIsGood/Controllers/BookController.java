package com.example.ReadingIsGood.Controllers;

import com.example.ReadingIsGood.Authentication.AuthenticationResponse;
import com.example.ReadingIsGood.Models.BookModel;
import com.example.ReadingIsGood.Repositories.BookRepository;
import com.example.ReadingIsGood.Services.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    private final BookRepository bookRepository;

    @GetMapping("/all")
    public List<BookModel> fetchAllBooks(){
        return bookService.getAllBooks();
    }

    @PostMapping("/add")
    public ResponseEntity<?> saveBook(@RequestBody BookModel bookModel){
        try {
            bookRepository.save(bookModel);
        }
        catch(Exception e){
            return ResponseEntity.ok(new AuthenticationResponse("Error adding " + bookModel.getName()));
        }
        return ResponseEntity.ok(new AuthenticationResponse("Successful add " + bookModel.getName()));
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<?> updateBook(@RequestBody int stock, @PathVariable String bookId){

        BookModel bookModel = bookService.getBookById(bookId);
        bookModel.setStock(bookModel.getStock() + stock);

        try {
            bookRepository.save(bookModel);
        }
        catch(Exception e){
            return ResponseEntity.ok(new AuthenticationResponse("Error updating stock " + bookModel.getName()));
        }
        return ResponseEntity.ok(new AuthenticationResponse("Successful stock update " + bookModel.getName()));
    }

}
