package com.example.ReadingIsGood.UnitTests.Controllers;

import com.example.ReadingIsGood.Controllers.BookController;
import com.example.ReadingIsGood.Models.BookModel;
import com.example.ReadingIsGood.Services.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;

import java.math.BigDecimal;
import static org.mockito.Mockito.*;

class BookControllerTest {

    /*private BookService bookService = mock(BookService.class);
    private BookController bookController = new BookController(bookService);
    private Authentication principal;

    @Test
    void getAllBooks() {
        bookController.getAllBooks(principal);
        verify(bookService).getAllBooks(principal);
    }

    @Test
    void saveBook() {
        BookModel bookModel = new BookModel("Hello World!", new BigDecimal(15), 5);
        when(bookService.addBook(principal, bookModel)).thenReturn("" + bookModel.getName() + " successfully added.");
        bookController.saveBook(principal,bookModel);
        verify(bookService).addBook(principal,bookModel);
    }

    @Test
    void updateBook() {
        BookModel bookModel = new BookModel("Hello World!", new BigDecimal(15), 5);
        bookModel.setId("1234");
        when(bookService.updateBook(principal,bookModel.getId(),"5")).thenReturn("Successful stock update " + bookModel.getName() + "");
        bookController.updateBook(principal, "5", bookModel.getId());
        verify(bookService).updateBook(principal, bookModel.getId(), "5");
    }*/
}