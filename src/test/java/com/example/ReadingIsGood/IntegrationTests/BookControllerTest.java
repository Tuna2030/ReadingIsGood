package com.example.ReadingIsGood.IntegrationTests;

import com.example.ReadingIsGood.Services.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest
class BookControllerTest {

    /*@Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    private Authentication principal;

    @Test
    void getAllBooks() throws Exception {
*//*        mockMvc.perform(get("/api/v1/books/all"))
                .andDo(print())
                .andExpect(status().isOk());
        verify(bookService).getAllBooks(principal);*//*
    }

    @Test
    void saveBook() {}

    @Test
    void updateBook() {}*/

}