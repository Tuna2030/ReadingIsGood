package com.example.ReadingIsGood.UnitTests.Services;

import com.example.ReadingIsGood.Models.BookModel;
import com.example.ReadingIsGood.Repositories.BookRepository;
import com.example.ReadingIsGood.Services.BookService;
import com.example.ReadingIsGood.Services.LogServices;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {

/*    private BookRepository bookRepository = mock(BookRepository.class);
    private LogServices logServices = mock(LogServices.class);
    private BookService bookService = new BookService(bookRepository, logServices);
    private Authentication principal;

    @Test
    void findBookModelById() {
        BookModel bookModel = new BookModel("Hello World!", new BigDecimal(20), 5);
        bookModel.setId("1234");
        when(bookRepository.findBookModelById( bookModel.getId() )).thenReturn(bookModel);
        assertEquals(bookModel, bookService.findBookModelById("1234"));
    }

    @Test
    void save() {
        BookModel bookModel = new BookModel("Hello World!", new BigDecimal(20), 5);
        bookService.save(bookModel);
        verify(bookRepository).save(bookModel);
    }

    @Test
    void getAllBooks() {
        bookService.getAllBooks(principal);
        verify(bookRepository).findAll();
    }

    @Test
    void addBook() {
        BookModel bookModel = new BookModel("Hello World!", new BigDecimal(20), 5);
        bookModel.setId("1234");
        assertEquals(bookModel.getName() + " successfully added.", bookService.addBook(principal,bookModel));
    }

    @Test
    void updateBook() {
        BookModel bookModel = new BookModel("Hello World!", new BigDecimal(20), 5);
        bookModel.setId("1234");
        when(bookRepository.findBookModelById(bookModel.getId())).thenReturn(bookModel);
        assertEquals("Successful stock update " + bookModel.getName(), bookService.updateBook(principal, bookModel.getId(), "5"));
    }*/
}